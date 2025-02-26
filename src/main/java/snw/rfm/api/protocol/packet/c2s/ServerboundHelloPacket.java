package snw.rfm.api.protocol.packet.c2s;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
public class ServerboundHelloPacket extends Packet<ServerboundPacketHandler> {
    public static final String TYPE = "hello";

    private final int protocolVersion;

    public ServerboundHelloPacket(int protocolVersion, String nonce) {
        super(nonce);
        this.protocolVersion = protocolVersion;
    }

    public ServerboundHelloPacket(ByteArrayDataInput input) {
        super(input);
        this.protocolVersion = input.readInt();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeInt(this.protocolVersion);
    }

    @Override
    public void handle(ServerboundPacketHandler handler) {
        handler.handleHello(this);
    }
}
