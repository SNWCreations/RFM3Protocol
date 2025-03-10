package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.object.ActionResponse;
import snw.rfm.api.protocol.packet.Packet;

import static snw.rfm.api.protocol.util.PacketHelper.writeEnum;

@Getter
@ToString
public class ClientboundActionResponsePacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "action_response";

    private final ActionResponse value;

    public ClientboundActionResponsePacket(ActionResponse actionResponse, String nonce) {
        super(nonce);
        this.value = actionResponse;
    }

    public ClientboundActionResponsePacket(ByteArrayDataInput input) {
        super(input);
        this.value = ActionResponse.READER.read(input);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        writeEnum(output, this.value);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleResponse(this);
    }
}
