package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.util.PacketReaders;
import snw.rfm.api.protocol.util.PacketWriters;

import java.util.UUID;

@Getter
public class ClientboundRemovePlayerPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "remove_player";

    private final UUID playerUUID;

    public ClientboundRemovePlayerPacket(UUID playerUUID, String nonce) {
        super(nonce);
        this.playerUUID = playerUUID;
    }

    public ClientboundRemovePlayerPacket(ByteArrayDataInput input) {
        super(input);
        this.playerUUID = PacketReaders.UUID.read(input);
    }

    @Override
    protected String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        PacketWriters.UUID.write(output, this.playerUUID);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleRemovePlayer(this);
    }
}
