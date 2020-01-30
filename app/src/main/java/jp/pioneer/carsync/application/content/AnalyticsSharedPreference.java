package jp.pioneer.carsync.application.content;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import jp.pioneer.carsync.domain.model.AlexaLanguageType;
import jp.pioneer.carsync.domain.model.LiveSimulationSetting;
import jp.pioneer.carsync.domain.model.SmallCarTaSettingType;
import jp.pioneer.carsync.domain.model.SoundEffectSettingType;
import jp.pioneer.carsync.domain.model.SoundFieldControlSettingType;
import jp.pioneer.carsync.domain.model.SoundFxSettingEqualizerType;
import jp.pioneer.carsync.domain.model.SuperTodorokiSetting;
import jp.pioneer.carsync.domain.model.TimeAlignmentSettingMode;

import static com.google.common.base.Preconditions.checkNotNull;

public class AnalyticsSharedPreference {
    /* キーの設定 */
    enum KeyConst {
        KEY_NAVI_APPS_INSTALLED("key_navi_apps_installed"),
        KEY_NAVI_APPS_SETTING("key_navi_apps_setting"),
        KEY_NAVI_APPS_LAST_SENT_DATE("key_navi_apps_last_sent_date"),
        KEY_MESSAGE_APPS_INSTALLED("key_message_apps_installed"),
        KEY_MESSAGE_APPS_LAST_SENT_DATE("key_message_apps_last_sent_date"),
        KEY_MUSIC_APPS_INSTALLED("key_music_apps_installed"),
        KEY_MUSIC_APPS_LAST_SENT_DATE("key_music_apps_last_sent_date"),
        KEY_YOUTUBE_LINK_USE("key_youtube_link_use"),
        KEY_YOUTUBE_LINK_USE_LAST_SENT_DATE("key_youtube_link_use_last_sent_date"),
        KEY_ALEXA_USE("key_alexa_use"),
        KEY_ALEXA_USE_LAST_SENT_DATE("key_alexa_use_last_sent_date"),
        KEY_ALEXA_LANGUAGE_SENT("key_alexa_language_sent"),
        KEY_SETTING_FX_EQUALIZER_SENT("key_setting_fx_equalizer_sent"),
        KEY_SETTING_FX_LIVE_SIMULATION_SFC_SENT("key_setting_fx_live_simulation_sfc_sent"),
        KEY_SETTING_FX_LIVE_SIMULATION_SE_SENT("key_setting_fx_live_simulation_se_sent"),
        KEY_SETTING_FX_SUPER_TODOROKI_SENT("key_setting_fx_super_todoroki_sent"),
        KEY_SETTING_FX_EASY_SOUND_FIT_SENT("key_setting_fx_easy_sound_fit_sent"),
        KEY_SETTING_FX_TIME_ALIGNMENT_SENT("key_setting_fx_time_alignment_sent"),
        ;

        /**/
        private String label;

        /* コンストラクタ */
        KeyConst(String label) {
            this.label = label;
        }

        /* 名称取得 */
        public String getLabel() {
            return this.label;
        }
    }

    private static final boolean DEFAULT_YOUTUBE_LINK_USE = false;
    private static final long DEFAULT_YOUTUBE_LINK_USE_LAST_SENT_DATE = 0;
    private static final boolean DEFAULT_ALEXA_USE = false;
    private static final long DEFAULT_ALEXA_USE_LAST_SENT_DATE = 0;
    private static final String DEFAULT_ALEXA_LANGUAGE_SENT = null;
    private static final String DEFAULT_SETTING_FX_EQUALIZER_SENT = null;
    private static final String DEFAULT_SETTING_FX_LIVE_SIMULATION_SFC_SENT = null;
    private static final String DEFAULT_SETTING_FX_LIVE_SIMULATION_SE_SENT = null;
    private static final String DEFAULT_SETTING_FX_SUPER_TODOROKI_SENT = null;
    private static final String DEFAULT_SETTING_FX_EASY_SOUND_FIT_SENT = null;
    private static final String DEFAULT_SETTING_FX_TIME_ALIGNMENT_SENT = null;
    private final SharedPreferences mPreferences;

    /**
     * コンストラクタ.
     *
     * @param preferences SharedPreferences
     * @throws NullPointerException {@code preferences}がnull
     */
    public AnalyticsSharedPreference(@NonNull SharedPreferences preferences) {
        mPreferences = checkNotNull(preferences);
    }

    /**
     * YoutubeLink機能設定が一度でもONに変更されたか否か取得.
     *
     * @return {@code true}:ONにした　{@code false}:ONにしてない。
     */
    boolean isYoutubeLinkUse() {
        return load(KeyConst.KEY_YOUTUBE_LINK_USE, DEFAULT_YOUTUBE_LINK_USE);
    }

    /**
     * YoutubeLink機能設定が一度でもONに変更されたか設定.
     *
     * @param value {@code true}:ONにした{@code false}:ONにしてない
     */
    void setYoutubeLinkUse(boolean value) {
        save(KeyConst.KEY_YOUTUBE_LINK_USE, value);
    }

    /**
     * YoutubeLink機能使用情報最終送信日時取得.
     *
     * @return long 日時
     */
    long getYoutubeLinkUseLastSentDate() {
        return load(KeyConst.KEY_YOUTUBE_LINK_USE_LAST_SENT_DATE, DEFAULT_YOUTUBE_LINK_USE_LAST_SENT_DATE);
    }

    /**
     * YoutubeLink機能使用情報最終送信日時設定.
     *
     * @param value 日時
     */
    void setYoutubeLinkUseLastSentDate(long value) {
        save(KeyConst.KEY_YOUTUBE_LINK_USE_LAST_SENT_DATE, value);
    }

    /**
     * Alexa機能設定が一度でもログインに成功したか否か取得.
     *
     * @return {@code true}:ONにした　{@code false}:ONにしてない。
     */
    boolean isAlexaUse() {
        return load(KeyConst.KEY_ALEXA_USE, DEFAULT_ALEXA_USE);
    }

    /**
     * Alexa機能設定が一度でもログインに成功したか設定.
     *
     * @param value {@code true}:ONにした{@code false}:ONにしてない
     */
    void setAlexaUse(boolean value) {
        save(KeyConst.KEY_ALEXA_USE, value);
    }

    /**
     * Alexa機能使用情報最終送信日時取得.
     *
     * @return long 日時
     */
    long getAlexaUseLastSentDate() {
        return load(KeyConst.KEY_ALEXA_USE_LAST_SENT_DATE, DEFAULT_ALEXA_USE_LAST_SENT_DATE);
    }

    /**
     * Alexa機能使用情報最終送信日時設定.
     *
     * @param value 日時
     */
    void setAlexaUseLastSentDate(long value) {
        save(KeyConst.KEY_ALEXA_USE_LAST_SENT_DATE, value);
    }

    /**
     * Alexa機能設定が一度でもログインに成功したか否か取得.
     *
     * @return AlexaLanguageType 設定
     */
    @Nullable
    AlexaLanguageType getAlexaLanguageSent() {
        String name = load(KeyConst.KEY_ALEXA_LANGUAGE_SENT, DEFAULT_ALEXA_LANGUAGE_SENT);
        if (name == null) {
            return null;
        } else {
            return AlexaLanguageType.valueOf(name);
        }
    }

    /**
     * Alexa機能設定が一度でもログインに成功したか設定.
     *
     * @param value AlexaLanguageType
     */
    void setAlexaLanguageSent(AlexaLanguageType value) {
        save(KeyConst.KEY_ALEXA_LANGUAGE_SENT, value.name());
    }

    /**
     * Fx設定-EQ設定前回送信値取得.
     *
     * @return SoundFxSettingEqualizerType 設定
     */
    @Nullable
    SoundFxSettingEqualizerType getFxEqualizerSent() {
        String name = load(KeyConst.KEY_SETTING_FX_EQUALIZER_SENT, DEFAULT_SETTING_FX_EQUALIZER_SENT);
        if (name == null) {
            return null;
        } else {
            return SoundFxSettingEqualizerType.valueOf(name);
        }
    }

    /**
     * Fx設定-EQ設定前回送信値設定.
     *
     * @param value 設定
     */
    void setFxEqualizerSent(SoundFxSettingEqualizerType value) {
        save(KeyConst.KEY_SETTING_FX_EQUALIZER_SENT, value.name());
    }

    /**
     * Fx設定-ライブシミュレーションSFC設定前回送信値取得.
     *
     * @return SoundFieldControlSettingType 設定
     */
    @Nullable
    SoundFieldControlSettingType getFxLiveSimulationSfcSent() {
        String name = load(KeyConst.KEY_SETTING_FX_LIVE_SIMULATION_SFC_SENT, DEFAULT_SETTING_FX_LIVE_SIMULATION_SFC_SENT);
        if (name == null) {
            return null;
        } else {
            return SoundFieldControlSettingType.valueOf(name);
        }
    }

    /**
     * Fx設定-ライブシミュレーションSFC設定前回送信値設定.
     *
     * @param value 設定
     */
    void setFxLiveSimulationSfcSent(SoundFieldControlSettingType value) {
        save(KeyConst.KEY_SETTING_FX_LIVE_SIMULATION_SFC_SENT, value.name());
    }

    /**
     * Fx設定-ライブシミュレーションSE設定前回送信値取得.
     *
     * @return SoundFieldControlSettingType 設定
     */
    @Nullable
    SoundEffectSettingType getFxLiveSimulationSeSent() {
        String name = load(KeyConst.KEY_SETTING_FX_LIVE_SIMULATION_SE_SENT, DEFAULT_SETTING_FX_LIVE_SIMULATION_SE_SENT);
        if (name == null) {
            return null;
        } else {
            return SoundEffectSettingType.valueOf(name);
        }
    }

    /**
     * Fx設定-ライブシミュレーションSE設定前回送信値設定.
     *
     * @param value 設定
     */
    void setFxLiveSimulationSeSent(SoundEffectSettingType value) {
        save(KeyConst.KEY_SETTING_FX_LIVE_SIMULATION_SE_SENT, value.name());
    }

    /**
     * Fx設定-スーパー轟設定前回送信値取得.
     *
     * @return SuperTodorokiSetting 設定
     */
    @Nullable
    SuperTodorokiSetting getFxSuperTodorokiSent() {
        String name = load(KeyConst.KEY_SETTING_FX_SUPER_TODOROKI_SENT, DEFAULT_SETTING_FX_SUPER_TODOROKI_SENT);
        if (name == null) {
            return null;
        } else {
            return SuperTodorokiSetting.valueOf(name);
        }
    }

    /**
     * Fx設定-スーパー轟設定前回送信値設定.
     *
     * @param value 設定
     */
    void setFxSuperTodorokiSent(SuperTodorokiSetting value) {
        save(KeyConst.KEY_SETTING_FX_SUPER_TODOROKI_SENT, value.name());
    }

    /**
     * Fx設定-イージーサウンドフィット設定前回送信値取得.
     *
     * @return SmallCarTaSettingType 設定
     */
    @Nullable
    SmallCarTaSettingType getFxEasySoundFitSent() {
        String name = load(KeyConst.KEY_SETTING_FX_EASY_SOUND_FIT_SENT, DEFAULT_SETTING_FX_EASY_SOUND_FIT_SENT);
        if (name == null) {
            return null;
        } else {
            return SmallCarTaSettingType.valueOf(name);
        }
    }

    /**
     * Fx設定-イージーサウンドフィット設定前回送信値設定.
     *
     * @param value 設定
     */
    void setFxEasySoundFitSent(SmallCarTaSettingType value) {
        save(KeyConst.KEY_SETTING_FX_EASY_SOUND_FIT_SENT, value.name());
    }

    /**
     * Fx設定-TA設定前回送信値取得.
     *
     * @return SuperTodorokiSetting 設定
     */
    @Nullable
    TimeAlignmentSettingMode getFxTimeAlignmentSent() {
        String name = load(KeyConst.KEY_SETTING_FX_TIME_ALIGNMENT_SENT, DEFAULT_SETTING_FX_TIME_ALIGNMENT_SENT);
        if (name == null) {
            return null;
        } else {
            return TimeAlignmentSettingMode.valueOf(name);
        }
    }

    /**
     * Fx設定-TA設定前回送信値設定.
     *
     * @param value 設定
     */
    void setFxTimeAlignmentSent(TimeAlignmentSettingMode value) {
        save(KeyConst.KEY_SETTING_FX_TIME_ALIGNMENT_SENT, value.name());
    }

    /**
     * 保存
     */
    private void save(KeyConst key, String value) {
        mPreferences.edit()
                .putString(key.getLabel(), value)
                .apply();
    }

    private void save(KeyConst key, boolean value) {
        mPreferences.edit()
                .putBoolean(key.getLabel(), value)
                .apply();
    }

    private void save(KeyConst key, int value) {
        mPreferences.edit()
                .putInt(key.getLabel(), value)
                .apply();
    }

    private void save(KeyConst key, long value) {
        mPreferences.edit()
                .putLong(key.getLabel(), value)
                .apply();
    }

    /**
     * 読込
     */
    private String load(KeyConst key) {
        return mPreferences.getString(key.getLabel(), "");
    }

    private String load(KeyConst key, String defVal) {
        return mPreferences.getString(key.getLabel(), defVal);
    }

    private boolean load(KeyConst key, boolean defVal) {
        return mPreferences.getBoolean(key.getLabel(), defVal);
    }

    private int load(KeyConst key, int defVal) {
        return mPreferences.getInt(key.getLabel(), defVal);
    }

    private long load(KeyConst key, long defVal) {
        return mPreferences.getLong(key.getLabel(), defVal);
    }

    /**
     * 削除
     */
    public void remove(KeyConst key) {
        mPreferences.edit()
                .remove(key.getLabel())
                .apply();
    }

    /**
     * 全削除
     */
    public void removeAll() {
        mPreferences.edit()
                .clear()
                .apply();
    }

}
