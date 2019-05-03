package io.lytng.graph;

public interface ModelMapper<MODEL> {

    public ExecutionGraph importModel(MODEL model);

}
