package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ServerboundSetGameFrozenPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "set_game_frozen";

    private final boolean nowFrozen;

    public ServerboundSetGameFrozenPacket(boolean nowFrozen, String nonce) {
        super(nonce);
        this.nowFrozen = nowFrozen;
    }

    public ServerboundSetGameFrozenPacket(ByteArrayDataInput input) {
        super(input);
        this.nowFrozen = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeBoolean(nowFrozen);
    }

    @Override
    public void handle(ServerboundPacketHandler handler) {
        handler.handleSetGameFrozen(this);
    }
}
