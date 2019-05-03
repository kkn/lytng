package io.lytng.graph;

import io.lytng.onnx.operator.Operator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class ExecutionGraph {

    private List<String> inputs;

    private List<String> outputs;

    private HashMap<String, Operator> operatorNodes;

    public ExecutionGraph() {
        operatorNodes = new LinkedHashMap<>();
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }

    public HashMap<String, Operator> getOperatorNodes() {
        return operatorNodes;
    }

    public void setOperatorNodes(HashMap<String, Operator> operatorNodes) {
        this.operatorNodes = operatorNodes;
    }

    public void setOperatorNode(String nodeId, Operator operatorNode) {
        this.operatorNodes.put(nodeId, operatorNode);
    }

    public Operator getOperatorNode(String nodeId) {
        return this.operatorNodes.getOrDefault(nodeId, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecutionGraph)) return false;
        ExecutionGraph that = (ExecutionGraph) o;
        return Objects.equals(getInputs(), that.getInputs()) &&
                Objects.equals(getOutputs(), that.getOutputs()) &&
                Objects.equals(getOperatorNodes(), that.getOperatorNodes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInputs(), getOutputs(), getOperatorNodes());
    }
}