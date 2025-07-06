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
public class ClientboundSetGameTimerPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "set_game_timer";

    private final String identifier;
    private final long timestamp;
    private final int newValue;

    public ClientboundSetGameTimerPacket(long timestamp, int newValue, String nonce) {
        this(ProtocolConst.TIMER_IDENTIFIER_GAME, timestamp, newValue, nonce);
    }

    public ClientboundSetGameTimerPacket(String identifier, long timestamp, int newValue, String nonce) {
        super(nonce);
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.newValue = newValue;
    }

    public ClientboundSetGameTimerPacket(ByteArrayDataInput input) {
        super(input);
        this.identifier = input.readUTF();
        this.timestamp = input.readLong();
        this.newValue = input.readInt();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeUTF(this.identifier);
        output.writeLong(this.timestamp);
        output.writeInt(this.newValue);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleSetGameTimer(this);
    }
}
