/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.source.dash.offline;

import static com.google.android.exoplayer2.source.dash.offline.DashDownloadTestData.TEST_MPD;
import static com.google.android.exoplayer2.source.dash.offline.DashDownloadTestData.TEST_MPD_NO_INDEX;
import static com.google.android.exoplayer2.source.dash.offline.DashDownloadTestData.TEST_MPD_URI;
import static com.google.android.exoplayer2.testutil.CacheAsserts.assertCacheEmpty;
import static com.google.android.exoplayer2.testutil.CacheAsserts.assertCachedData;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.android.exoplayer2.offline.DownloadException;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.source.dash.manifest.RepresentationKey;
import com.google.android.exoplayer2.testutil.FakeDataSet;
import com.google.android.exoplayer2.testutil.FakeDataSource;
import com.google.android.exoplayer2.testutil.FakeDataSource.Factory;
import com.google.android.exoplayer2.testutil.TestUtil;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

/** Unit tests for {@link DashDownloader}. */
@RunWith(RobolectricTestRunner.class)
public class DashDownloaderTest {

  private SimpleCache cache;
  private File tempFolder;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    tempFolder = Util.createTempDirectory(RuntimeEnvironment.application, "ExoPlayerTest");
    cache = new SimpleCache(tempFolder, new NoOpCacheEvictor());
  }

  @After
  public void tearDown() {
    Util.recursiveDelete(tempFolder);
  }

  @Test
  public void testDownloadRepresentation() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6);

    DashDownloader dashDownloader = getDashDownloader(fakeDataSet, new RepresentationKey(0, 0, 0));
    dashDownloader.download();
    assertCachedData(cache, fakeDataSet);
  }

  @Test
  public void testDownloadRepresentationInSmallParts() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .newData("audio_segment_1")
            .appendReadData(TestUtil.buildTestData(10))
            .appendReadData(TestUtil.buildTestData(10))
            .appendReadData(TestUtil.buildTestData(10))
            .endData()
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6);

    DashDownloader dashDownloader = getDashDownloader(fakeDataSet, new RepresentationKey(0, 0, 0));
    dashDownloader.download();
    assertCachedData(cache, fakeDataSet);
  }

  @Test
  public void testDownloadRepresentations() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6)
            .setRandomData("text_segment_1", 1)
            .setRandomData("text_segment_2", 2)
            .setRandomData("text_segment_3", 3);

    DashDownloader dashDownloader =
        getDashDownloader(
            fakeDataSet, new RepresentationKey(0, 0, 0), new RepresentationKey(0, 1, 0));
    dashDownloader.download();
    assertCachedData(cache, fakeDataSet);
  }

  @Test
  public void testDownloadAllRepresentations() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6)
            .setRandomData("text_segment_1", 1)
            .setRandomData("text_segment_2", 2)
            .setRandomData("text_segment_3", 3)
            .setRandomData("period_2_segment_1", 1)
            .setRandomData("period_2_segment_2", 2)
            .setRandomData("period_2_segment_3", 3);

    DashDownloader dashDownloader = getDashDownloader(fakeDataSet);
    dashDownloader.download();
    assertCachedData(cache, fakeDataSet);
  }

  @Test
  public void testProgressiveDownload() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6)
            .setRandomData("text_segment_1", 1)
            .setRandomData("text_segment_2", 2)
            .setRandomData("text_segment_3", 3);
    FakeDataSource fakeDataSource = new FakeDataSource(fakeDataSet);
    Factory factory = mock(Factory.class);
    when(factory.createDataSource()).thenReturn(fakeDataSource);

    DashDownloader dashDownloader =
        getDashDownloader(factory, new RepresentationKey(0, 0, 0), new RepresentationKey(0, 1, 0));
    dashDownloader.download();

    DataSpec[] openedDataSpecs = fakeDataSource.getAndClearOpenedDataSpecs();
    assertThat(openedDataSpecs.length).isEqualTo(8);
    assertThat(openedDataSpecs[0].uri).isEqualTo(TEST_MPD_URI);
    assertThat(openedDataSpecs[1].uri.getPath()).isEqualTo("audio_init_data");
    assertThat(openedDataSpecs[2].uri.getPath()).isEqualTo("audio_segment_1");
    assertThat(openedDataSpecs[3].uri.getPath()).isEqualTo("text_segment_1");
    assertThat(openedDataSpecs[4].uri.getPath()).isEqualTo("audio_segment_2");
    assertThat(openedDataSpecs[5].uri.getPath()).isEqualTo("text_segment_2");
    assertThat(openedDataSpecs[6].uri.getPath()).isEqualTo("audio_segment_3");
    assertThat(openedDataSpecs[7].uri.getPath()).isEqualTo("text_segment_3");
  }

  @Test
  public void testProgressiveDownloadSeparatePeriods() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6)
            .setRandomData("period_2_segment_1", 1)
            .setRandomData("period_2_segment_2", 2)
            .setRandomData("period_2_segment_3", 3);
    FakeDataSource fakeDataSource = new FakeDataSource(fakeDataSet);
    Factory factory = mock(Factory.class);
    when(factory.createDataSource()).thenReturn(fakeDataSource);

    DashDownloader dashDownloader =
        getDashDownloader(factory, new RepresentationKey(0, 0, 0), new RepresentationKey(1, 0, 0));
    dashDownloader.download();

    DataSpec[] openedDataSpecs = fakeDataSource.getAndClearOpenedDataSpecs();
    assertThat(openedDataSpecs.length).isEqualTo(8);
    assertThat(openedDataSpecs[0].uri).isEqualTo(TEST_MPD_URI);
    assertThat(openedDataSpecs[1].uri.getPath()).isEqualTo("audio_init_data");
    assertThat(openedDataSpecs[2].uri.getPath()).isEqualTo("audio_segment_1");
    assertThat(openedDataSpecs[3].uri.getPath()).isEqualTo("audio_segment_2");
    assertThat(openedDataSpecs[4].uri.getPath()).isEqualTo("audio_segment_3");
    assertThat(openedDataSpecs[5].uri.getPath()).isEqualTo("period_2_segment_1");
    assertThat(openedDataSpecs[6].uri.getPath()).isEqualTo("period_2_segment_2");
    assertThat(openedDataSpecs[7].uri.getPath()).isEqualTo("period_2_segment_3");
  }

  @Test
  public void testDownloadRepresentationFailure() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .newData("audio_segment_2")
            .appendReadData(TestUtil.buildTestData(2))
            .appendReadError(new IOException())
            .appendReadData(TestUtil.buildTestData(3))
            .endData()
            .setRandomData("audio_segment_3", 6);

    DashDownloader dashDownloader = getDashDownloader(fakeDataSet, new RepresentationKey(0, 0, 0));
    try {
      dashDownloader.download();
      fail();
    } catch (IOException e) {
      // Expected.
    }
    dashDownloader.download();
    assertCachedData(cache, fakeDataSet);
  }

  @Test
  public void testCounters() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .newData("audio_segment_2")
            .appendReadData(TestUtil.buildTestData(2))
            .appendReadError(new IOException())
            .appendReadData(TestUtil.buildTestData(3))
            .endData()
            .setRandomData("audio_segment_3", 6);

    DashDownloader dashDownloader = getDashDownloader(fakeDataSet, new RepresentationKey(0, 0, 0));
    assertThat(dashDownloader.getDownloadedBytes()).isEqualTo(0);

    try {
      dashDownloader.download();
      fail();
    } catch (IOException e) {
      // Failure expected after downloading init data, segment 1 and 2 bytes in segment 2.
    }
    assertThat(dashDownloader.getDownloadedBytes()).isEqualTo(10 + 4 + 2);

    dashDownloader.download();
    assertThat(dashDownloader.getDownloadedBytes()).isEqualTo(10 + 4 + 5 + 6);
  }

  @Test
  public void testRemove() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD)
            .setRandomData("audio_init_data", 10)
            .setRandomData("audio_segment_1", 4)
            .setRandomData("audio_segment_2", 5)
            .setRandomData("audio_segment_3", 6)
            .setRandomData("text_segment_1", 1)
            .setRandomData("text_segment_2", 2)
            .setRandomData("text_segment_3", 3);

    DashDownloader dashDownloader =
        getDashDownloader(
            fakeDataSet, new RepresentationKey(0, 0, 0), new RepresentationKey(0, 1, 0));
    dashDownloader.download();
    dashDownloader.remove();
    assertCacheEmpty(cache);
  }

  @Test
  public void testRepresentationWithoutIndex() throws Exception {
    FakeDataSet fakeDataSet =
        new FakeDataSet()
            .setData(TEST_MPD_URI, TEST_MPD_NO_INDEX)
            .setRandomData("test_segment_1", 4);

    DashDownloader dashDownloader = getDashDownloader(fakeDataSet, new RepresentationKey(0, 0, 0));
    try {
      dashDownloader.download();
      fail();
    } catch (DownloadException e) {
      // Expected.
    }
    dashDownloader.remove();
    assertCacheEmpty(cache);
  }

  private DashDownloader getDashDownloader(FakeDataSet fakeDataSet, RepresentationKey... keys) {
    return getDashDownloader(new Factory(null).setFakeDataSet(fakeDataSet), keys);
  }

  private DashDownloader getDashDownloader(Factory factory, RepresentationKey... keys) {
    return new DashDownloader(
        TEST_MPD_URI, keysList(keys), new DownloaderConstructorHelper(cache, factory));
  }

  private static ArrayList<RepresentationKey> keysList(RepresentationKey... keys) {
    ArrayList<RepresentationKey> keysList = new ArrayList<>();
    Collections.addAll(keysList, keys);
    return keysList;
  }

}
