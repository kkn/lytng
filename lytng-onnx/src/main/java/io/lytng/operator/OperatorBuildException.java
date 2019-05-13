package io.lytng.operator;

public class OperatorBuildException extends RuntimeException {

    public OperatorBuildException(String message) {
        super(message);
    }

    public OperatorBuildException(Throwable cause) {
        super(cause);
    }

    public OperatorBuildException(String message, Throwable cause) {
        super(message, cause);
    }

}
