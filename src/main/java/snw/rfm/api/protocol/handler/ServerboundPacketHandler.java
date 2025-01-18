package snw.rfm.api.protocol.handler;

import snw.rfm.api.protocol.packet.c2s.ServerboundHelloPacket;
import snw.rfm.api.protocol.packet.c2s.ServerboundSetGameTimerPacket;
import snw.rfm.api.protocol.packet.c2s.ServerboundSetPlayerTeamPacket;
import snw.rfm.api.protocol.packet.c2s.ServerboundSetTimerFrozenPacket;

public interface ServerboundPacketHandler extends PacketHandler {
    void handleHello(ServerboundHelloPacket packet);

    void handleSetGameTimer(ServerboundSetGameTimerPacket packet);

    void handleSetPlayerTeam(ServerboundSetPlayerTeamPacket packet);

    void handleSetTimerFrozen(ServerboundSetTimerFrozenPacket packet);
}
