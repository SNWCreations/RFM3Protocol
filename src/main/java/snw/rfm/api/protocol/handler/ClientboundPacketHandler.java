package snw.rfm.api.protocol.handler;

import snw.rfm.api.protocol.packet.s2c.*;

public interface ClientboundPacketHandler extends PacketHandler {

    void handleAddPlayer(ClientboundAddPlayerPacket packet);

    void handleEndGame(ClientboundEndGamePacket packet);

    void handleHello(ClientboundHelloPacket packet);

    void handleRemovePlayer(ClientboundRemovePlayerPacket packet);

    void handleResponse(ClientboundActionResponsePacket packet);

    void handleSetGameFrozen(ClientboundSetGameFrozenPacket packet);

    void handleSetGameTimer(ClientboundSetGameTimerPacket packet);

    void handleSetPlayerAdminStatus(ClientboundSetPlayerAdminStatusPacket packet);

    void handleSetPlayerStatus(ClientboundSetPlayerStatusPacket packet);

    void handleSetTimerFrozen(ClientboundSetTimerFrozenPacket packet);

    void handleStartGame(ClientboundStartGamePacket packet);
}
