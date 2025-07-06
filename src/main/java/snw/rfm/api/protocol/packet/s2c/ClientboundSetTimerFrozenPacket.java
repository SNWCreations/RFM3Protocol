package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.ProtocolConst;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ClientboundSetTimerFrozenPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_timer_frozen";

    private final String identifier;
    private final long timestamp;
    private final boolean nowFrozen;

    public ClientboundSetTimerFrozenPacket(long timestamp, boolean nowFrozen, String nonce) {
        this(ProtocolConst.TIMER_IDENTIFIER_GAME, timestamp, nowFrozen, nonce);
    }

    public ClientboundSetTimerFrozenPacket(String identifier, long timestamp, boolean nowFrozen, String nonce) {
        super(nonce);
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.nowFrozen = nowFrozen;
    }

    public ClientboundSetTimerFrozenPacket(ByteArrayDataInput input) {
        super(input);
        this.identifier = input.readUTF();
        this.timestamp = input.readLong();
        this.nowFrozen = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeUTF(this.identifier);
        output.writeLong(this.timestamp);
        output.writeBoolean(this.nowFrozen);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetTimerFrozen(this);
    }
}
