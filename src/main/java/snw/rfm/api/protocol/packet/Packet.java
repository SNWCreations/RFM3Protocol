package snw.rfm.api.protocol.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import snw.rfm.api.protocol.handler.PacketHandler;

@Getter
public abstract class Packet<T extends PacketHandler> {
    private final String nonce;

    protected Packet(String nonce) {
        this.nonce = nonce;
    }

    // use this to do deserialize
    protected Packet(ByteArrayDataInput input) {
        nonce = input.readUTF();
    }

    public final byte[] serialize() {
        // noinspection UnstableApiUsage
        final ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(getType());
        output.writeUTF(nonce);
        doSerialization(output);
        return output.toByteArray();
    }

    public abstract String getType();

    // write data to the output in the implementations
    public abstract void doSerialization(ByteArrayDataOutput output);

    public abstract void handle(T handler);
}
