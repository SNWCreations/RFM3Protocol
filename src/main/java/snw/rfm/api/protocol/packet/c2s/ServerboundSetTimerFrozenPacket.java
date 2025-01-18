package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
public class ServerboundSetTimerFrozenPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "set_timer_frozen";

    private final boolean nowFrozen;

    public ServerboundSetTimerFrozenPacket(boolean nowFrozen, String nonce) {
        super(nonce);
        this.nowFrozen = nowFrozen;
    }

    public ServerboundSetTimerFrozenPacket(ByteArrayDataInput input) {
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
    public void handle(ServerboundPacketHandler handler) {
        handler.handleSetTimerFrozen(this);
    }
}
