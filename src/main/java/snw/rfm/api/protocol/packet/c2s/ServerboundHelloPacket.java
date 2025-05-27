package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ServerboundHelloPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "hello";

    private final int protocolVersion;
    private final boolean receiveAllPlayerAbilities;

    public ServerboundHelloPacket(int protocolVersion, boolean receiveAllPlayerAbilities, String nonce) {
        super(nonce);
        this.protocolVersion = protocolVersion;
        this.receiveAllPlayerAbilities = receiveAllPlayerAbilities;
    }

    public ServerboundHelloPacket(ByteArrayDataInput input) {
        super(input);
        this.protocolVersion = input.readInt();
        this.receiveAllPlayerAbilities = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeInt(this.protocolVersion);
        output.writeBoolean(this.receiveAllPlayerAbilities);
    }

    @Override
    public void handle(ServerboundPacketHandler handler) {
        handler.handleHello(this);
    }
}
