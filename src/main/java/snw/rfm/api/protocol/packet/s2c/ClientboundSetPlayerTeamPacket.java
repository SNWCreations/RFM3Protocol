package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.util.PacketReaders;
import snw.rfm.api.protocol.util.PacketWriters;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static snw.rfm.api.protocol.util.PacketHelper.*;

@Getter
@ToString
public class ClientboundSetPlayerTeamPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_player_team";

    private final @Nullable String targetTeam;
    private final Set<UUID> targets;

    public ClientboundSetPlayerTeamPacket(Set<UUID> targets, @Nullable String targetTeam, String nonce) {
        super(nonce);
        this.targetTeam = targetTeam;
        this.targets = targets;
    }

    public ClientboundSetPlayerTeamPacket(ByteArrayDataInput input) {
        super(input);
        this.targetTeam = readOptional(input, ByteArrayDataInput::readUTF);
        this.targets = readCollection(input, HashSet::new, PacketReaders.UUID);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        writeOptional(output, this.targetTeam, ByteArrayDataOutput::writeUTF);
        writeCollection(output, this.targets, PacketWriters.UUID);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetPlayerTeam(this);
    }
}
