package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.util.PacketReaders;
import snw.rfm.api.protocol.util.PacketWriters;

import java.util.UUID;

@Getter
@ToString
public class ClientboundAddPlayerPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "add_player";

    private final UUID playerUUID;
    private final boolean admin;

    public ClientboundAddPlayerPacket(UUID playerUUID, boolean admin, String nonce) {
        super(nonce);
        this.playerUUID = playerUUID;
        this.admin = admin;
    }

    public ClientboundAddPlayerPacket(ByteArrayDataInput input) {
        super(input);
        this.playerUUID = PacketReaders.UUID.read(input);
        this.admin = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        PacketWriters.UUID.write(output, this.playerUUID);
        output.writeBoolean(this.admin);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleAddPlayer(this);
    }
}
