package jp.pioneer.carsync.infrastructure.crp.handler.setting.illumi;

import android.support.annotation.NonNull;

import jp.pioneer.carsync.domain.model.IlluminationSetting;
import jp.pioneer.carsync.domain.model.SphBtPhoneColorSetting;
import jp.pioneer.carsync.domain.model.StatusHolder;
import jp.pioneer.carsync.infrastructure.crp.BadPacketException;
import jp.pioneer.carsync.infrastructure.crp.IncomingPacket;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;
import static jp.pioneer.carsync.infrastructure.crp.util.PacketUtil.checkPacketDataLength;

/**
 * [SPH] BT PHONE COLOR設定情報パケットプロセッサ.
 * <p>
 * [SPH] BT PHONE COLOR設定情報応答と通知で使用する。
 */
public class SphBtPhoneColorSettingInfoPacketProcessor {
    private static final int DATA_LENGTH = 2;
    private StatusHolder mStatusHolder;

    /**
     * コンストラクタ.
     *
     * @param statusHolder StatusHolder
     * @throws NullPointerException {@code statusHolder}がnull
     */
    public SphBtPhoneColorSettingInfoPacketProcessor(@NonNull StatusHolder statusHolder) {
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

            IlluminationSetting illuminationSetting = mStatusHolder.getIlluminationSetting();
            // D1:BT PHONE COLOR設定値
            illuminationSetting.sphBtPhoneColorSetting = SphBtPhoneColorSetting.valueOf(data[1]);

            illuminationSetting.updateVersion();
            Timber.d("process() SphBtPhoneColorSetting = " + illuminationSetting.sphBtPhoneColorSetting);
            return Boolean.TRUE;
        } catch (BadPacketException | IllegalArgumentException e) {
            Timber.e(e, "process()");
            return Boolean.FALSE;
        }
    }

}
