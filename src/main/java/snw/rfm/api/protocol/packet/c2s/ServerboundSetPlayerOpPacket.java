package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
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
@ToString
public class ServerboundSetPlayerOpPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "set_player_op";

    private final Set<UUID> targets;
    private final boolean beingOp;

    public ServerboundSetPlayerOpPacket(Set<UUID> targets, boolean beingOp, String nonce) {
        super(nonce);
        this.targets = targets;
        this.beingOp = beingOp;
    }

    public ServerboundSetPlayerOpPacket(ByteArrayDataInput input) {
        super(input);
        this.targets = readCollection(input, HashSet::new, PacketReaders.UUID);
        this.beingOp = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        writeCollection(output, this.targets, PacketWriters.UUID);
        output.writeBoolean(this.beingOp);
    }

    @Override
    public void handle(ServerboundPacketHandler handler) {
        handler.handleSetPlayerOp(this);
    }
}
