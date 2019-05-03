package io.lytng.util;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;

public class NDArrayUtils {

    private NDArrayUtils() {
        super();
    }

    public static INDArray convertFloatListToArray(List<Float> list) {
        float[] array = new float[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return Nd4j.create(array);
    }
}