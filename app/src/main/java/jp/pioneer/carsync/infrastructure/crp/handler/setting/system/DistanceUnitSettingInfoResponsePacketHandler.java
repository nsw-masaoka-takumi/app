package jp.pioneer.carsync.infrastructure.crp.handler.setting.system;

import android.support.annotation.NonNull;

import com.google.common.base.Objects;

import jp.pioneer.carsync.infrastructure.crp.CarRemoteSession;
import jp.pioneer.carsync.infrastructure.crp.IncomingPacket;
import jp.pioneer.carsync.infrastructure.crp.handler.DataResponsePacketHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 距離単位設定情報応答パケットハンドラ.
 */
public class DistanceUnitSettingInfoResponsePacketHandler extends DataResponsePacketHandler {
    private CarRemoteSession mSession;
    private DistanceUnitSettingInfoPacketProcessor mPacketProcessor;

    /**
     * コンストラクタ.
     *
     * @param session CarRemoteSession
     * @throws NullPointerException {@code session}がnull
     */
    public DistanceUnitSettingInfoResponsePacketHandler(@NonNull CarRemoteSession session) {
        mSession = checkNotNull(session);
        mPacketProcessor = new DistanceUnitSettingInfoPacketProcessor(session.getStatusHolder());
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void handle(@NonNull IncomingPacket packet) throws Exception {
        setResult(mPacketProcessor.process(packet));
        if (Objects.equal(getResult(), Boolean.TRUE)) {
            mSession.publishStatusUpdateEvent(packet.getPacketIdType());
        }
    }
}