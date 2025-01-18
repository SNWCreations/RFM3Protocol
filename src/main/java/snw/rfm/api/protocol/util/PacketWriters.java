package snw.rfm.api.protocol.util;

import java.util.UUID;

public final class PacketWriters {
    public static final PacketWriter<UUID> UUID;

    static {
        UUID = (output, uuid) -> {
            long most = uuid.getMostSignificantBits();
            long least = uuid.getLeastSignificantBits();
            output.writeLong(most);
            output.writeLong(least);
        };
    }

    private PacketWriters() {
    }
}
