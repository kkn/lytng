package io.lytng.onnx.type;

import java.util.HashMap;
import java.util.Map;

public class MultiOutputContainer implements NodeOutputContainer {

    private Map<String, Object> values = new HashMap<>();

    @Override
    public <T> T get(String name) {
        @SuppressWarnings("unchecked")
        T result = (T) values.getOrDefault(name, null);

        return result;
    }

    @Override
    public <T> void put(String name, T output) {
        values.put(name, output);
    }

    @Override
    public <T> void putAll(Map<String, T> map) {
        values.putAll(map);
    }

    @Override
    public boolean containsKey(String name) {
        return values.containsKey(name);
    }

    @Override
    public void clear() {
        values.clear();
    }
}
