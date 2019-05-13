package io.lytng.operator;

import io.lytng.onnx.annotation.OnnxOperator;
import org.reflections.Reflections;

import java.util.Set;

public class OnnxOperatorBuilderFactory extends AbstractOperatorBuilderFactory {
    public static final OnnxOperatorBuilderFactory INSTANCE = new OnnxOperatorBuilderFactory();

    private OnnxOperatorBuilderFactory() {
        super();

        // TODO Operator packages should be configurable
        Reflections reflections = new Reflections("io.lytng.onnx.operator");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(OnnxOperator.class);

        for (Class<?> clazz : classes) {
            OnnxOperator annotation = clazz.getAnnotation(OnnxOperator.class);
            operatorsMap.put(annotation.opType(), annotation.builder());
        }
    }

}
