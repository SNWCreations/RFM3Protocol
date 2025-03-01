package snw.rfm.api.protocol;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteArrayDataInput;
import snw.rfm.api.protocol.handler.ClientboundPacketHandler;
import snw.rfm.api.protocol.handler.ServerboundPacketHandler;
import snw.rfm.api.protocol.packet.Packet;
import snw.rfm.api.protocol.handler.PacketHandler;
import snw.rfm.api.protocol.packet.c2s.*;
import snw.rfm.api.protocol.packet.s2c.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public final class PacketTypeSet<T extends PacketHandler> {
    public static final PacketTypeSet<ServerboundPacketHandler> SERVERBOUND;
    public static final PacketTypeSet<ClientboundPacketHandler> CLIENTBOUND;

    static {
        SERVERBOUND = new PacketTypeSet<>();
        CLIENTBOUND = new PacketTypeSet<>();
        registerServerboundPackets();
        registerClientboundPackets();
    }

    private final Map<String, Function<ByteArrayDataInput, ? extends Packet<T>>> deserializers;

    private PacketTypeSet() {
        this.deserializers = new HashMap<>();
    }

    public void register(String typeName, Function<ByteArrayDataInput, ? extends Packet<T>> deserializer) {
        Preconditions.checkState(!this.deserializers.containsKey(typeName), "Packet type %s already registered", typeName);
        this.deserializers.put(typeName, deserializer);
    }

    public Function<ByteArrayDataInput, ? extends Packet<T>> getDeserializer(String typeName) {
        return this.deserializers.get(typeName);
    }

    private static <T> void operate(T object, Consumer<T> initializer) {
        initializer.accept(object);
    }

    private static void registerServerboundPackets() {
        operate(SERVERBOUND, it -> {
            it.register(ServerboundHelloPacket.TYPE, ServerboundHelloPacket::new);
            it.register(ServerboundSetGameTimerPacket.TYPE, ServerboundSetGameTimerPacket::new);
            it.register(ServerboundSetPlayerOpPacket.TYPE, ServerboundSetPlayerOpPacket::new);
            it.register(ServerboundSetPlayerTeamPacket.TYPE, ServerboundSetPlayerTeamPacket::new);
            it.register(ServerboundSetTimerFrozenPacket.TYPE, ServerboundSetTimerFrozenPacket::new);
        });
    }

    private static void registerClientboundPackets() {
        operate(CLIENTBOUND, it -> {
            it.register(ClientboundActionResponsePacket.TYPE, ClientboundActionResponsePacket::new);
            it.register(ClientboundAddPlayerPacket.TYPE, ClientboundAddPlayerPacket::new);
            it.register(ClientboundEndGamePacket.TYPE, ClientboundEndGamePacket::new);
            it.register(ClientboundHelloPacket.TYPE, ClientboundHelloPacket::new);
            it.register(ClientboundRemovePlayerPacket.TYPE, ClientboundRemovePlayerPacket::new);
            it.register(ClientboundSetGameFrozenPacket.TYPE, ClientboundSetGameFrozenPacket::new);
            it.register(ClientboundSetGameTimerPacket.TYPE, ClientboundSetGameTimerPacket::new);
            it.register(ClientboundSetPlayerAdminStatusPacket.TYPE, ClientboundSetPlayerAdminStatusPacket::new);
            it.register(ClientboundSetPlayerStatusPacket.TYPE, ClientboundSetPlayerStatusPacket::new);
            it.register(ClientboundSetTimerFrozenPacket.TYPE, ClientboundSetTimerFrozenPacket::new);
            it.register(ClientboundStartGamePacket.TYPE, ClientboundStartGamePacket::new);
        });
    }
}
