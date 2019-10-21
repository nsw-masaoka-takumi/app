package jp.pioneer.carsync.infrastructure.crp.handler.setting.soundfx;

import android.support.annotation.NonNull;

import jp.pioneer.carsync.domain.model.ListeningPosition;
import jp.pioneer.carsync.domain.model.SmallCarTaSetting;
import jp.pioneer.carsync.domain.model.SmallCarTaSettingType;
import jp.pioneer.carsync.domain.model.SoundFxSetting;
import jp.pioneer.carsync.domain.model.StatusHolder;
import jp.pioneer.carsync.infrastructure.crp.BadPacketException;
import jp.pioneer.carsync.infrastructure.crp.IncomingPacket;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;
import static jp.pioneer.carsync.infrastructure.crp.util.PacketUtil.checkPacketDataLength;

/**
 * Small Car TA設定情報パケットプロセッサ.
 * <p>
 * Small Car TA設定情報応答と通知で使用する。
 */
public class SmallCarTaSettingInfoPacketProcessor {
    private static final int DATA_LENGTH = 3;
    private StatusHolder mStatusHolder;

    /**
     * コンストラクタ.
     *
     * @param statusHolder StatusHolder
     * @throws NullPointerException {@code statusHolder}がnull
     */
    public SmallCarTaSettingInfoPacketProcessor(@NonNull StatusHolder statusHolder) {
        mStatusHolder = checkNotNull(statusHolder);
    }

    /**
     * 処理.
     *
     * @param packet 受信パケット
     * @return {@link Boolean#TRUE}:成功。{@link Boolean#FALSE}:それ以外。
     * @throws NullPointerException {@code packet}がnull
     */
    public Boolean process(@NonNull IncomingPacket packet) {
        try {
            byte[] data = checkNotNull(packet).getData();
            checkPacketDataLength(data, DATA_LENGTH);

            SoundFxSetting soundFxSetting = mStatusHolder.getSoundFxSetting();
            SmallCarTaSetting setting = soundFxSetting.smallCarTaSetting;
            // D1:Small Car TA設定
            setting.smallCarTaSettingType = SmallCarTaSettingType.valueOf(data[1]);
            // D2:Listening Position設定
            setting.listeningPosition = ListeningPosition.valueOf(data[2]);

            soundFxSetting.updateVersion();
            Timber.d("process() SmallCarTaSetting = " + setting);
            return Boolean.TRUE;
        } catch (BadPacketException | IllegalArgumentException e) {
            Timber.e(e, "process()");
            return Boolean.FALSE;
        }
    }
}
