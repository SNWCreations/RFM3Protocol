package snw.rfm.api.protocol.util;

import com.google.common.io.ByteArrayDataInput;

public interface PacketReader<T> {
    T read(ByteArrayDataInput input);
}
