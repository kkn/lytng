package io.lytng.onnx.graph;

import io.lytng.graph.ExecutionGraph;
import io.lytng.graph.ModelMapper;

public abstract class AbstractModelMapper<T> implements ModelMapper<T> {

    AbstractModelMapper() {
        super();
    }

    public ExecutionGraph importModel(T model) {
        return this.generateExecutionGraph(model);
    }

    public abstract ExecutionGraph generateExecutionGraph(T model);
}