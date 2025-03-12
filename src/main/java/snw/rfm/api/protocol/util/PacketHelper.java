package snw.rfm.api.protocol.util;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.function.IntFunction;

public final class PacketHelper {

    public static String newNonce() {
        return UUID.randomUUID().toString();
    }

    public static void writeEnum(ByteArrayDataOutput output, Enum<?> enumConst) {
        output.writeInt(enumConst.ordinal());
    }

    public static <K, V, M extends Map<K, V>> M readMap(ByteArrayDataInput input, IntFunction<M> mFactory, PacketReader<K> keyReader, PacketReader<V> valueReader) {
        int size = input.readInt();
        M map = mFactory.apply(size);
        for (int i = 0; i < size; i++) {
            K k = keyReader.read(input);
            V v = valueReader.read(input);
            map.put(k, v);
        }
        return map;
    }

    public static <K, V> void writeMap(ByteArrayDataOutput output, Map<K, V> map, PacketWriter<K> keyWriter, PacketWriter<V> valueWriter) {
        output.writeInt(map.size());
        map.forEach((k, v) -> {
            keyWriter.write(output, k);
            valueWriter.write(output, v);
        });
    }

    public static <E, T extends Collection<E>> T readCollection(ByteArrayDataInput input, IntFunction<T> cFactory, PacketReader<E> reader) {
        int size = input.readInt();
        T collection = cFactory.apply(size);
        for (int i = 0; i < size; i++) {
            E e = reader.read(input);
            collection.add(e);
        }
        return collection;
    }

    public static <T> void writeCollection(ByteArrayDataOutput output, Collection<T> collection, PacketWriter<T> writer) {
        output.writeInt(collection.size());
        for (T element : collection) {
            writer.write(output, element);
        }
    }

    public static <T> @Nullable T readOptional(ByteArrayDataInput input, PacketReader<T> reader) {
        T t;
        if (input.readBoolean()) {
            t = reader.read(input);
        } else {
            t = null;
        }
        return t;
    }

    public static <T> void writeOptional(ByteArrayDataOutput output, @Nullable T value, PacketWriter<T> writer) {
        boolean notNull = value != null;
        output.writeBoolean(notNull);
        if (notNull) {
            writer.write(output, value);
        }
    }

    private PacketHelper() {
    }
}
