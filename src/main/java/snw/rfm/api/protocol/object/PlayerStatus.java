package snw.rfm.api.protocol.object;

import snw.rfm.api.protocol.util.PacketReader;

import static snw.rfm.api.protocol.util.PacketReaders.enumReader;

public enum PlayerStatus {
    ALIVE,
    OUT,
    ABANDON
    ;

    public static final PacketReader<PlayerStatus> READER = enumReader(PlayerStatus.class);
}
