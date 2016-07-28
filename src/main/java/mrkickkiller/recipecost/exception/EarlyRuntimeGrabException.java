package mrkickkiller.recipecost.exception;

/**
 * Created by Mathieu on 28/07/2016.
 */
public class EarlyRuntimeGrabException extends RuntimeException {
    public EarlyRuntimeGrabException() {
    }

    public EarlyRuntimeGrabException(String message) {
        super(message);
    }

    public EarlyRuntimeGrabException(String message, Throwable cause) {
        super(message, cause);
    }

    public EarlyRuntimeGrabException(Throwable cause) {
        super(cause);
    }
}
