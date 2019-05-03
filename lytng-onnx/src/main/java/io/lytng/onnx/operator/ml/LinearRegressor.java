package io.lytng.onnx.operator.ml;

import io.lytng.onnx.annotation.OnnxOperator;
import io.lytng.onnx.operator.AbstractOperator;
import io.lytng.onnx.type.NodeOutputContainer;
import io.lytng.util.NDArrayUtils;
import onnx.OnnxMlProto3;
import onnx.OnnxProto3;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

@OnnxOperator(opType = "LinearRegressor", builder = LinearRegressor.Builder.class)
public class LinearRegressor extends AbstractOperator {

    private INDArray coefficients;

    private INDArray intercepts;

    private LinearRegressor(List<String> inputs, List<String> outputs, INDArray intercepts, INDArray coefficients) {
        super(inputs, outputs);
        this.coefficients = coefficients;
        this.intercepts = intercepts;
    }

    @Override
    public void evaluate(NodeOutputContainer context) {
        INDArray input = context.get(inputs.get(0));
        INDArray result = intercepts.add(coefficients.mul(input));

        context.put(outputs.get(0), result);
    }

    public static class Builder extends AbstractOperator.Builder {
        private final String COEFFICIENTS = "coefficients";
        private final String INTERCEPTS = "intercepts";
        private final String POST_TRANSFORM = "post_transform";
        private final String TARGETS = "targets";

        private INDArray coefficients;

        private INDArray intercepts;

        @Override
        public Builder attribute(OnnxMlProto3.AttributeProto attribute) {
            switch (attribute.getName()) {
                case COEFFICIENTS:
                    coefficients = NDArrayUtils.convertFloatListToArray(attribute.getFloatsList());
                    break;
                case INTERCEPTS:
                    intercepts = NDArrayUtils.convertFloatListToArray(attribute.getFloatsList());
                    break;
                case POST_TRANSFORM:
                case TARGETS:
                default:
                    break;
            }

            return this;
        }

        @Override
        public Builder attribute(OnnxProto3.AttributeProto attribute) {
            return this;
        }

        @Override
        public LinearRegressor build() {
            return new LinearRegressor(
                    inputs,
                    outputs,
                    intercepts,
                    coefficients);
        }
    }
}