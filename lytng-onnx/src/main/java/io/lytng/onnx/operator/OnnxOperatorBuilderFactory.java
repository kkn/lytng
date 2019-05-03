package io.lytng.onnx.operator;

import io.lytng.onnx.annotation.OnnxOperator;
import io.lytng.onnx.exception.LytngException;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OnnxOperatorBuilderFactory {
    public static final OnnxOperatorBuilderFactory INSTANCE = new OnnxOperatorBuilderFactory();

    private Map<String, Class<?>> operatorsMap = new HashMap<>();

    private OnnxOperatorBuilderFactory() {
        // TODO Operator packages should be configurable
        Reflections reflections = new Reflections("io.lytng.onnx.operator");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(OnnxOperator.class);

        for (Class<?> clazz : classes) {
            OnnxOperator annotation = clazz.getAnnotation(OnnxOperator.class);
            operatorsMap.put(annotation.opType(), annotation.builder());
        }
    }

    public OperatorBuilder getBuilder(String opType) {
        Class<?> operatorBuilderClass = operatorsMap.getOrDefault(opType, null);
        Object operatorBuilder;

        if (null == operatorBuilderClass) {
            throw new LytngException("Operator not found for opType " + opType);
        }

        try {
            Constructor<?> constructor = operatorBuilderClass.getConstructor();
            operatorBuilder = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException e) {
            throw new LytngException("Builder not found for opType " + opType);
        }

        if (operatorBuilder instanceof OperatorBuilder) {
            return (OperatorBuilder) operatorBuilder;
        } else {
            throw new LytngException("Incompatible Builder for opType " + opType);
        }
    }
}
