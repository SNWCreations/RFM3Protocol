package snw.rfm.api.protocol.handler;

import snw.rfm.api.protocol.packet.c2s.*;

public interface ServerboundPacketHandler extends PacketHandler {
    void handleHello(ServerboundHelloPacket packet);

    void handleSetGameFrozen(ServerboundSetGameFrozenPacket packet);

    void handleSetGameTimer(ServerboundSetGameTimerPacket packet);

    void handleSetPlayerOp(ServerboundSetPlayerOpPacket packet);

    void handleSetPlayerTeam(ServerboundSetPlayerTeamPacket packet);

    void handleSetTimerFrozen(ServerboundSetTimerFrozenPacket packet);
}
