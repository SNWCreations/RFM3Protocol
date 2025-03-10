package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ClientboundSetTimerFrozenPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_timer_frozen";

    private final long timestamp;
    private final boolean nowFrozen;

    public ClientboundSetTimerFrozenPacket(long timestamp, boolean nowFrozen, String nonce) {
        super(nonce);
        this.timestamp = timestamp;
        this.nowFrozen = nowFrozen;
    }

    public ClientboundSetTimerFrozenPacket(ByteArrayDataInput input) {
        super(input);
        this.timestamp = input.readLong();
        this.nowFrozen = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeLong(this.timestamp);
        output.writeBoolean(this.nowFrozen);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetTimerFrozen(this);
    }
}
