package io.lytng.predictor;

import io.lytng.graph.ExecutionGraph;
import io.lytng.onnx.type.MultiOutputContainer;
import io.lytng.onnx.type.NodeOutputContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MLPredictor {

    protected static final Logger LOG = LoggerFactory.getLogger(MLPredictor.class);

    private final ExecutionGraph executionGraph;

    private NodeOutputContainer nodeOutputs = new MultiOutputContainer();

    public MLPredictor(final ExecutionGraph executionGraph) {
        this.executionGraph = executionGraph;
    }

    public synchronized Map<String, Object> predict(Map<String, Object> inputs) {
        nodeOutputs.clear();

        nodeOutputs.putAll(inputs);

        executionGraph.getOperatorNodes()
                .values()
                .forEach(operator -> operator.evaluate(nodeOutputs));

        return executionGraph.getOutputs()
                .stream()
                .filter(nodeOutputs::containsKey)
                .collect(Collectors.toMap(Function.identity(), nodeOutputs::get));
    }
}