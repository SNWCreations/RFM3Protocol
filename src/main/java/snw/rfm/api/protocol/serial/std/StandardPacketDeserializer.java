package snw.rfm.api.protocol.serial.std;

import com.google.common.io.ByteArrayDataInput;
import snw.rfm.api.protocol.PacketTypeSet;
import snw.rfm.api.protocol.handler.PacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.serial.PacketContainer;
import snw.rfm.api.protocol.serial.PacketDeserializer;

import java.util.function.Function;

public class StandardPacketDeserializer<T extends PacketHandler> implements PacketDeserializer<T> {
    private final PacketTypeSet<T> packetTypeSet;

    public StandardPacketDeserializer(PacketTypeSet<T> packetTypeSet) {
        this.packetTypeSet = packetTypeSet;
    }

    @Override
    public PacketContainer<T> deserializePacket(ByteArrayDataInput input) {
        final String type = input.readUTF();
        final String nonce;
        final Packet<T> packet;
        final Function<ByteArrayDataInput, ? extends Packet<T>> deserializer;
        deserializer = packetTypeSet.getDeserializer(type);
        if (deserializer != null) {
            packet = deserializer.apply(input);
            nonce = packet.getNonce();
        } else {
            packet = null;
            nonce = input.readUTF();
        }
        return new PacketContainer<>(type, nonce, packet);
    }
}
