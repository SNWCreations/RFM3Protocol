package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
public class ClientboundSetTimerFrozenPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_timer_frozen";

    private final boolean nowFrozen;

    public ClientboundSetTimerFrozenPacket(boolean nowFrozen, String nonce) {
        super(nonce);
        this.nowFrozen = nowFrozen;
    }

    public ClientboundSetTimerFrozenPacket(ByteArrayDataInput input) {
        super(input);
        this.nowFrozen = input.readBoolean();
    }

    @Override
    protected String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeBoolean(this.nowFrozen);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetTimerFrozen(this);
    }
}
