package io.lytng.onnx.exception;

public class LytngInferenceException extends RuntimeException {

    public LytngInferenceException(String message) {
        super(message);
    }

    public LytngInferenceException(Throwable cause) {
        super(cause);
    }

    public LytngInferenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
