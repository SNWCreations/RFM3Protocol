package snw.rfm.api.protocol.util;

import snw.rfm.api.game.GamePlayerAbilities;

import java.util.UUID;

public final class PacketWriters {
    public static final PacketWriter<UUID> UUID;
    public static final PacketWriter<GamePlayerAbilities> PLAYER_ABILITIES;

    static {
        UUID = (output, uuid) -> {
            long most = uuid.getMostSignificantBits();
            long least = uuid.getLeastSignificantBits();
            output.writeLong(most);
            output.writeLong(least);
        };
        PLAYER_ABILITIES = (output, abilities) -> {
            final boolean inGame = abilities.isInGame();
            final boolean mayBeCaught = abilities.isMayBeCaught();
            final boolean canBeCaught = abilities.isCanBeCaught();
            final boolean canCatchOthers = abilities.isCanCatchOthers();
            final boolean canAbandon = abilities.isCanAbandon();
            final boolean canReceiveNotifications = abilities.isCanReceiveNotifications();
            output.writeBoolean(inGame);
            output.writeBoolean(mayBeCaught);
            output.writeBoolean(canBeCaught);
            output.writeBoolean(canCatchOthers);
            output.writeBoolean(canAbandon);
            output.writeBoolean(canReceiveNotifications);
        };
    }

    private PacketWriters() {
    }
}
