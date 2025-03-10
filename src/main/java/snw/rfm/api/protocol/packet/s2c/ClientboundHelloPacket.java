package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ClientboundHelloPacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "hello";
    private final boolean protocolIncompatible;

    public ClientboundHelloPacket(boolean protocolIncompatible, String nonce) {
        super(nonce);
        this.protocolIncompatible = protocolIncompatible;
    }

    public ClientboundHelloPacket(ByteArrayDataInput input) {
        super(input);
        this.protocolIncompatible = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeBoolean(this.protocolIncompatible);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleHello(this);
    }
}
