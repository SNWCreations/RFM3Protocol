package snw.rfm.api.protocol.object;

import snw.rfm.api.protocol.util.PacketReader;

import static snw.rfm.api.protocol.util.PacketReaders.enumReader;

public enum ActionResponse {
    SUCCESS,
    EXCEPTION,
    NO_GAME,
    NO_PERMISSION,
    UNSUPPORTED
    ;

    public static final PacketReader<ActionResponse> READER = enumReader(ActionResponse.class);
}
