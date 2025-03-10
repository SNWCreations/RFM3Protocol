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
public class ClientboundSetPlayerAdminStatusPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_player_admin_status";

    private final UUID playerUUID;
    private final boolean admin;

    public ClientboundSetPlayerAdminStatusPacket(UUID playerUUID, boolean admin, String nonce) {
        super(nonce);
        this.playerUUID = playerUUID;
        this.admin = admin;
    }

    public ClientboundSetPlayerAdminStatusPacket(ByteArrayDataInput input) {
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
        handler.handleSetPlayerAdminStatus(this);
    }
}
