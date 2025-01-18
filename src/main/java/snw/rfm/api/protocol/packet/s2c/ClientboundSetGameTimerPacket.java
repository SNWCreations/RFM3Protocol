package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
public class ClientboundSetGameTimerPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_game_timer";

    private final int newValue;

    public ClientboundSetGameTimerPacket(int newValue, String nonce) {
        super(nonce);
        this.newValue = newValue;
    }

    public ClientboundSetGameTimerPacket(ByteArrayDataInput input) {
        super(input);
        this.newValue = input.readInt();
    }

    @Override
    protected String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeInt(this.newValue);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetGameTimer(this);
    }
}
