package io.lytng.graph;

import com.google.common.collect.ImmutableMap;
import io.lytng.operator.Operator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExecutionGraph {

    private List<String> inputs;
    private List<String> outputs;
    private ImmutableMap<String, Operator> operatorNodes;

    private ExecutionGraph(Builder builder) {
        inputs = builder.inputs;
        outputs = builder.outputs;
        operatorNodes = ImmutableMap.copyOf(builder.operatorNodes);
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public ImmutableMap<String, Operator> getOperatorNodes() {
        return operatorNodes;
    }

    public static class Builder {
        private List<String> inputs;
        private List<String> outputs;
        private Map<String, Operator> operatorNodes = new LinkedHashMap<>();

        public Builder inputs(List<String> inputs) {
            this.inputs = inputs;
            return this;
        }

        public Builder outputs(List<String> outputs) {
            this.outputs = outputs;
            return this;
        }

        @SuppressWarnings("UnusedReturnValue")
        public Builder operatorNode(String nodeId, Operator operator) {
            this.operatorNodes.put(nodeId, operator);
            return this;
        }

        public ExecutionGraph build() {
            return new ExecutionGraph(this);
        }
    }
}
