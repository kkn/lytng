package io.lytng.onnx.graph;

import io.lytng.graph.ExecutionGraph;
import io.lytng.graph.ModelMapper;
import io.lytng.onnx.exception.LytngException;
import io.lytng.operator.OnnxOperatorBuilderFactory;
import io.lytng.operator.Operator;
import io.lytng.operator.OperatorBuildException;
import onnx.OnnxMlProto3;

import java.util.stream.Collectors;


public class OnnxMlModelMapper implements ModelMapper<OnnxMlProto3.ModelProto> {
    public static final OnnxMlModelMapper INSTANCE = new OnnxMlModelMapper();

    private OnnxMlModelMapper() {
        super();
    }

    @Override
    public ExecutionGraph importModel(OnnxMlProto3.ModelProto model) {
        OnnxMlProto3.GraphProto graph = model.getGraph();

        ExecutionGraph.Builder executionGraphBuilder = new ExecutionGraph.Builder()
                .inputs(
                        graph.getInputList()
                                .stream()
                                .map(OnnxMlProto3.ValueInfoProto::getName)
                                .collect(Collectors.toList()))
                .outputs(
                        graph.getOutputList()
                                .stream()
                                .map(OnnxMlProto3.ValueInfoProto::getName)
                                .collect(Collectors.toList())
                );

        try {
            int i = 0;
            for (OnnxMlProto3.NodeProto node : graph.getNodeList()) {
                String nodeId = (null == node.getName()) ? String.valueOf(i) : node.getName();
                executionGraphBuilder.operatorNode(nodeId, generateOperatorNode(node));
            }
        } catch (OperatorBuildException e) {
            throw new LytngException(e);
        }

        return executionGraphBuilder.build();
    }

    private Operator generateOperatorNode(OnnxMlProto3.NodeProto node) {
        return OnnxOperatorBuilderFactory.INSTANCE.getBuilder(node.getOpType())
                .node(node)
                .build();
    }
}