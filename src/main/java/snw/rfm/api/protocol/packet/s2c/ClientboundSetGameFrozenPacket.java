package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ClientboundSetGameFrozenPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_game_frozen";

    private final boolean nowFrozen;

    public ClientboundSetGameFrozenPacket(boolean nowFrozen, String nonce) {
        super(nonce);
        this.nowFrozen = nowFrozen;
    }

    public ClientboundSetGameFrozenPacket(ByteArrayDataInput input) {
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
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetGameFrozen(this);
    }
}
