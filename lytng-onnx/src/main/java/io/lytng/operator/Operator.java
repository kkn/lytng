package io.lytng.operator;

import io.lytng.onnx.type.NodeOutputContainer;

public interface Operator {

    public void evaluate(NodeOutputContainer context);

}
