package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.game.GamePlayerAbilities;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.util.PacketReaders;
import snw.rfm.api.protocol.util.PacketWriters;

import java.util.UUID;

@ToString
@Getter
public class ClientboundPlayerAbilitiesPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "player_abilities";

    private final UUID dataOwner;
    private final GamePlayerAbilities updated;

    public ClientboundPlayerAbilitiesPacket(UUID dataOwner, GamePlayerAbilities updated, String nonce) {
        super(nonce);
        this.dataOwner = dataOwner;
        this.updated = updated;
    }

    public ClientboundPlayerAbilitiesPacket(ByteArrayDataInput input) {
        super(input);
        this.dataOwner = PacketReaders.UUID.read(input);
        this.updated = PacketReaders.PLAYER_ABILITIES.read(input);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        PacketWriters.UUID.write(output, dataOwner);
        PacketWriters.PLAYER_ABILITIES.write(output, this.updated);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handlePlayerAbilities(this);
    }
}
