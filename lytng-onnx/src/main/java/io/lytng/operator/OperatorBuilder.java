package io.lytng.operator;

import onnx.OnnxMlProto3;
import onnx.OnnxProto3;

import java.util.List;

public interface OperatorBuilder {

    OperatorBuilder inputs(List<String> inputs);

    OperatorBuilder outputs(List<String> outputs);

    OperatorBuilder node(OnnxMlProto3.NodeProto node);

    OperatorBuilder node(OnnxProto3.NodeProto node);

    Operator build();
}
