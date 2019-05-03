package io.lytng.onnx.exception;

public class LytngException extends RuntimeException {

    public LytngException(String message) {
        super(message);
    }

    public LytngException(Throwable cause) {
        super(cause);
    }

    public LytngException(String message, Throwable cause) {
        super(message, cause);
    }

}
