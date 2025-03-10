package snw.rfm.api.protocol.packet.s2c;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import lombok.ToString;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;

@Getter
@ToString
public class ClientboundStartGamePacket extends Packet<ClientboundPacketHandler> {
    public static final String TYPE = "start_game";

    private final long timestamp;
    private final int releaseTimer;
    private final boolean gameStartAfterReleaseTimer;

    public ClientboundStartGamePacket(long timestamp, int releaseTimer, boolean gameStartAfterReleaseTimer, String nonce) {
        super(nonce);
        this.timestamp = timestamp;
        this.releaseTimer = releaseTimer;
        this.gameStartAfterReleaseTimer = gameStartAfterReleaseTimer;
    }

    public ClientboundStartGamePacket(ByteArrayDataInput input) {
        super(input);
        this.timestamp = input.readLong();
        this.releaseTimer = input.readInt();
        this.gameStartAfterReleaseTimer = input.readBoolean();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void doSerialization(ByteArrayDataOutput output) {
        output.writeLong(this.timestamp);
        output.writeInt(this.releaseTimer);
        output.writeBoolean(this.gameStartAfterReleaseTimer);
    }

    @Override
    public void handle(ClientboundPacketHandler handler) {
        handler.handleStartGame(this);
    }
}
