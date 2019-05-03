package io.lytng.onnx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OnnxOperator {
    String opType();

    Class<?> builder();
}
