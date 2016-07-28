package mrkickkiller.recipecost.exception;

/**
 * Created by MrKickkiller on 28/07/2016.
 *
 * Custom exception to handle the case where the JEIruntime is being used before it was given by @link{JEI}
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
