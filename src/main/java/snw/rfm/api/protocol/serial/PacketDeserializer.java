package snw.rfm.api.protocol.serial;

import com.google.common.io.ByteArrayDataInput;
import snw.rfm.api.protocol.handler.PacketHandler;

public interface PacketDeserializer<T extends PacketHandler> {
    PacketContainer<T> deserializePacket(ByteArrayDataInput input);
}
