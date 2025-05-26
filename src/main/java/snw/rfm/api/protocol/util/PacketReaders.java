package snw.rfm.api.protocol.util;

import snw.rfm.api.game.GamePlayerAbilities;

import java.util.UUID;

public final class PacketReaders {
    public static final PacketReader<UUID> UUID;
    public static final PacketReader<GamePlayerAbilities> PLAYER_ABILITIES;

    static {
        UUID = input -> {
            long most = input.readLong();
            long least = input.readLong();
            return new UUID(most, least);
        };
        PLAYER_ABILITIES = input -> {
            final GamePlayerAbilities result = new GamePlayerAbilities();
            final boolean inGame = input.readBoolean();
            result.setInGame(inGame);
            return result;
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
