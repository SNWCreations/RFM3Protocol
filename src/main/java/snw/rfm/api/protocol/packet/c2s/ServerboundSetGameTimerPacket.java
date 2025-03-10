package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ServerboundSetGameTimerPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "set_game_timer";

    private final int newValue;

    public ServerboundSetGameTimerPacket(int newValue, String nonce) {
        super(nonce);
        this.newValue = newValue;
    }

    public ServerboundSetGameTimerPacket(ByteArrayDataInput input) {
        super(input);
        this.newValue = input.readInt();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeInt(this.newValue);
    }

    @Override
    public void handle(ServerboundPacketHandler handler) {
        handler.handleSetGameTimer(this);
    }
}
