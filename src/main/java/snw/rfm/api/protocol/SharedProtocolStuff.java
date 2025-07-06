package snw.rfm.api.protocol;

public final class SharedProtocolStuff {
    public static final String CHANNEL = "rfm:main";
    public static final int PROTOCOL_VERSION = 3;
    public static final int LAST_INCOMPATIBLE_PROTOCOL_VERSION = 2;

    public static boolean compatibleProtocol(int version) {
        if (version == PROTOCOL_VERSION) {
            return true;
        } else if (version > PROTOCOL_VERSION) { // Newer than us, we'll fail
            return false;
        } else {
            // Will be true if we're still compatible
            // (No protocol breaking changes which affects the C/S communication)
            return version > LAST_INCOMPATIBLE_PROTOCOL_VERSION;
        }
    }

    private SharedProtocolStuff() {
    }
}
