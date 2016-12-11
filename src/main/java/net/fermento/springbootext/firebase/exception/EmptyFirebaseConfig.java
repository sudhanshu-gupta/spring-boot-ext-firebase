package net.fermento.springbootext.firebase.exception;

/**
 * Created by sgupta on 12/11/16.
 */
public class EmptyFirebaseConfig extends RuntimeException {

    public EmptyFirebaseConfig(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFirebaseConfig() {
    }

    public EmptyFirebaseConfig(String message) {
        super(message);
    }

    public EmptyFirebaseConfig(Throwable cause) {
        super(cause);
    }
}
