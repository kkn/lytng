package io.lytng.onnx.operator;

import io.lytng.onnx.type.NodeOutputContainer;

public interface Operator {

    public void evaluate(NodeOutputContainer context);

}
