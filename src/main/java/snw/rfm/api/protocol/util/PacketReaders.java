package snw.rfm.api.protocol.util;

import java.util.UUID;

public final class PacketReaders {
    public static final PacketReader<UUID> UUID;

    static {
        UUID = input -> {
            long most = input.readLong();
            long least = input.readLong();
            return new UUID(most, least);
        };
    }

    public static <E> PacketReader<E> enumReader(Class<E> clazz) {
        return input -> {
            int ordinal = input.readInt();
            return clazz.getEnumConstants()[ordinal];
        };
    }

    private PacketReaders() {
    }
}
