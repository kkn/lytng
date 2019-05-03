package io.lytng.onnx.type;

import java.util.Map;

public interface NodeOutputContainer {

    public <T> T get(String name);

    public <T> void put(String name, T output);

    public <T> void putAll(Map<String, T> map);

    public boolean containsKey(String key);

    public void clear();

}