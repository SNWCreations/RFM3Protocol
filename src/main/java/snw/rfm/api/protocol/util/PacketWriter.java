package snw.rfm.api.protocol.util;

import com.google.common.io.ByteArrayDataOutput;

public interface PacketWriter<T> {
    void write(ByteArrayDataOutput output, T object);
}
