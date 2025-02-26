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
public class ClientboundAddPlayerPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "add_player";

    private final UUID playerUUID;
    private final boolean admin;
    private final PlayerStatus playerStatus;

    public ClientboundAddPlayerPacket(UUID playerUUID, boolean admin, PlayerStatus playerStatus, String nonce) {
        super(nonce);
        this.playerUUID = playerUUID;
        this.admin = admin;
        this.playerStatus = playerStatus;
    }

    public ClientboundAddPlayerPacket(ByteArrayDataInput input) {
        super(input);
        this.playerUUID = PacketReaders.UUID.read(input);
        this.admin = input.readBoolean();
        this.playerStatus = PlayerStatus.READER.read(input);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        PacketWriters.UUID.write(output, this.playerUUID);
        output.writeBoolean(this.admin);
        writeEnum(output, this.playerStatus);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleAddPlayer(this);
    }
}
