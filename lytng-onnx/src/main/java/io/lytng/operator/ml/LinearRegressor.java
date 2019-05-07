package io.lytng.operator.ml;

import io.lytng.onnx.annotation.OnnxOperator;
import io.lytng.onnx.type.NodeOutputContainer;
import io.lytng.operator.AbstractOperator;
import io.lytng.util.NDArrayUtils;
import onnx.OnnxMlProto3;
import onnx.OnnxProto3;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * LinearRegressor operator implementation.
 */
@OnnxOperator(opType = "LinearRegressor", builder = LinearRegressor.Builder.class)
public class LinearRegressor extends AbstractOperator {

    private INDArray coefficients;
    private INDArray intercepts;
    private int target;
    private String postTransform;

    private LinearRegressor(Builder builder) {
        super(builder);
        coefficients = builder.coefficients;
        intercepts = builder.intercepts;
        target = builder.target;
        postTransform = builder.postTransform;
    }

    @Override
    public void evaluate(NodeOutputContainer context) {
        INDArray input = context.get(inputs.get(0));
        INDArray result = intercepts.add(coefficients.mul(input));

        context.put(outputs.get(0), result);
    }

    public static class Builder extends AbstractOperator.Builder {
        private INDArray coefficients;
        private INDArray intercepts;
        private int target = 1;
        private String postTransform = "NONE";

        @Override
        public Builder attribute(OnnxMlProto3.AttributeProto attribute) {
            switch (attribute.getName()) {
                case "coefficients":
                    coefficients = NDArrayUtils.convertFloatListToArray(attribute.getFloatsList());
                    break;
                case "intercepts":
                    intercepts = NDArrayUtils.convertFloatListToArray(attribute.getFloatsList());
                    break;
                case "post_transform":
                case "targets":
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
            // throw exception if parameters are not valid
            return new LinearRegressor(this);
        }
    }
}