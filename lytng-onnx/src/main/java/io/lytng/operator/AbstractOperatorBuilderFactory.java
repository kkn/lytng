package io.lytng.operator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOperatorBuilderFactory {

    protected Map<String, Class<?>> operatorsMap = new HashMap<>();

    public OperatorBuilder getBuilder(String opType) {
        Class<?> operatorBuilderClass = operatorsMap.getOrDefault(opType, null);
        Object operatorBuilder;

        if (null == operatorBuilderClass) {
            throw new OperatorBuildException("Operator not found for opType " + opType);
        }

        try {
            Constructor<?> constructor = operatorBuilderClass.getConstructor();
            operatorBuilder = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException e) {
            throw new OperatorBuildException("Builder not found for opType " + opType);
        }

        if (operatorBuilder instanceof OperatorBuilder) {
            return (OperatorBuilder) operatorBuilder;
        } else {
            throw new OperatorBuildException("Incompatible Builder for opType " + opType);
        }
    }
}
