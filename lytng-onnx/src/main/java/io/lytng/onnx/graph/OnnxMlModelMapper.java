package io.lytng.onnx.graph;

import io.lytng.graph.ExecutionGraph;
import io.lytng.onnx.operator.OnnxOperatorBuilderFactory;
import io.lytng.onnx.operator.Operator;
import io.lytng.onnx.operator.OperatorBuilder;
import onnx.OnnxMlProto3;

import java.util.stream.Collectors;

public class OnnxMlModelMapper extends AbstractModelMapper<OnnxMlProto3.ModelProto> {
    public static final OnnxMlModelMapper INSTANCE = new OnnxMlModelMapper();

    private OnnxMlModelMapper() {
        super();
    }

    @Override
    public ExecutionGraph generateExecutionGraph(OnnxMlProto3.ModelProto model) {
        OnnxMlProto3.GraphProto graph = model.getGraph();

        ExecutionGraph executionGraph = new ExecutionGraph();

        executionGraph.setInputs(
                graph.getInputList()
                        .stream()
                        .map(OnnxMlProto3.ValueInfoProto::getName)
                        .collect(Collectors.toList()));

        executionGraph.setOutputs(
                graph.getOutputList()
                        .stream()
                        .map(OnnxMlProto3.ValueInfoProto::getName)
                        .collect(Collectors.toList()));

        int i = 0;
        for (OnnxMlProto3.NodeProto node : graph.getNodeList()) {
            String nodeId = (null == node.getName()) ? String.valueOf(i) : node.getName();
            executionGraph.setOperatorNode(nodeId, generateOperatorNode(node));
        }

        return executionGraph;
    }

    private Operator generateOperatorNode(OnnxMlProto3.NodeProto node) {
        OperatorBuilder builder = OnnxOperatorBuilderFactory.INSTANCE.getBuilder(node.getOpType());
        return builder.node(node).build();
    }
}