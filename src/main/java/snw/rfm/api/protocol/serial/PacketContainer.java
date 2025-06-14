package snw.rfm.api.protocol.serial;

import org.jetbrains.annotations.Nullable;
import snw.rfm.api.protocol.handler.PacketHandler;
import snw.rfm.api.protocol.packet.Packet;

public record PacketContainer<T extends PacketHandler>(
        String type,
        String nonce,
        // This is null when packet definition is not found
        @Nullable Packet<T> packet
) {
}
