package io.lytng.operator;

import onnx.OnnxMlProto3;
import onnx.OnnxProto3;

import java.util.List;

public abstract class AbstractOperator implements Operator {

    protected List<String> inputs;
    protected List<String> outputs;

    protected AbstractOperator(Builder builder) {
        inputs = builder.inputs;
        outputs = builder.outputs;
    }

    protected abstract static class Builder implements OperatorBuilder {
        protected List<String> inputs;
        protected List<String> outputs;

        public Builder inputs(List<String> inputs) {
            this.inputs = inputs;
            return this;
        }

        public Builder outputs(List<String> outputs) {
            this.outputs = outputs;
            return this;
        }

        public abstract Builder attribute(OnnxMlProto3.AttributeProto attribute);

        public abstract Builder attribute(OnnxProto3.AttributeProto attribute);

        public Builder node(OnnxMlProto3.NodeProto node) {
            this.inputs(node.getInputList()).outputs(node.getOutputList());

            node.getAttributeList()
                    .forEach(this::attribute);

            return this;
        }

        public Builder node(OnnxProto3.NodeProto node) {
            this.inputs(node.getInputList()).outputs(node.getOutputList());

            node.getAttributeList()
                    .forEach(this::attribute);

            return this;
        }
    }
}
