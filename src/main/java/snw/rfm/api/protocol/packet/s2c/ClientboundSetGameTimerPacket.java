package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
public class ClientboundSetGameTimerPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_game_timer";

    private final long timestamp;
    private final int newValue;

    public ClientboundSetGameTimerPacket(long timestamp, int newValue, String nonce) {
        super(nonce);
        this.timestamp = timestamp;
        this.newValue = newValue;
    }

    public ClientboundSetGameTimerPacket(ByteArrayDataInput input) {
        super(input);
        this.timestamp = input.readLong();
        this.newValue = input.readInt();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeLong(this.timestamp);
        output.writeInt(this.newValue);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetGameTimer(this);
    }
}
