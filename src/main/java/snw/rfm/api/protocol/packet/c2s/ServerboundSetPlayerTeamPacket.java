package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.util.PacketReaders;
import snw.rfm.api.protocol.util.PacketWriters;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static snw.rfm.api.protocol.util.PacketHelper.readCollection;
import static snw.rfm.api.protocol.util.PacketHelper.writeCollection;

@Getter
public class ServerboundSetPlayerTeamPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "set_player_team";

    private final String targetTeam;
    private final Set<UUID> targets;

    public ServerboundSetPlayerTeamPacket(Set<UUID> targets, String targetTeam, String nonce) {
        super(nonce);
        this.targetTeam = targetTeam;
        this.targets = targets;
    }

    public ServerboundSetPlayerTeamPacket(ByteArrayDataInput input) {
        super(input);
        this.targetTeam = input.readUTF();
        this.targets = readCollection(input, HashSet::new, PacketReaders.UUID);
    }

    @Override
    protected String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeUTF(this.targetTeam);
        writeCollection(output, this.targets, PacketWriters.UUID);
    }

    @Override
    public void handle(ServerboundPacketHandler handler) {
        handler.handleSetPlayerTeam(this);
    }
}
