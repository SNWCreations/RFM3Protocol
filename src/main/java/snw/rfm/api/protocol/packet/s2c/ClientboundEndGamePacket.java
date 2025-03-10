package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@ToString
public class ClientboundEndGamePacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "end_game";

    public ClientboundEndGamePacket(String nonce) {
        super(nonce);
    }

    public ClientboundEndGamePacket(ByteArrayDataInput input) {
        super(input);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleEndGame(this);
    }
}
