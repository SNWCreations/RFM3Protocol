package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.object.PlayerStatus;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.util.PacketReaders;
import snw.rfm.api.protocol.util.PacketWriters;

import java.util.UUID;

import static snw.rfm.api.protocol.util.PacketHelper.writeEnum;

@Getter
public class ClientboundSetPlayerStatusPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_player_status";

    private final UUID playerUUID;
    private final PlayerStatus newStatus;

    public ClientboundSetPlayerStatusPacket(UUID playerUUID, PlayerStatus newStatus, String nonce) {
        super(nonce);
        this.playerUUID = playerUUID;
        this.newStatus = newStatus;
    }

    public ClientboundSetPlayerStatusPacket(ByteArrayDataInput input) {
        super(input);
        this.playerUUID = PacketReaders.UUID.read(input);
        this.newStatus = PlayerStatus.READER.read(input);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        PacketWriters.UUID.write(output, this.playerUUID);
        writeEnum(output, this.newStatus);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetPlayerStatus(this);
    }
}
