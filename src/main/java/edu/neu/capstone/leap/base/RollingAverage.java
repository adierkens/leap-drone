package edu.neu.capstone.leap.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 8/13/15.
 */
public class RollingAverage<T extends Number> {

    private int maxSize;
    private List<T> numbers;

    public RollingAverage(int size) {
        maxSize = size;
        clear();
    }

    public void add(T num) {
        if (numbers.size() == maxSize) {
            for (int i = 1; i < numbers.size(); i++) {
                numbers.set(i - 1, numbers.get(i));
            }
            numbers.remove(maxSize - 1);
        }
        numbers.add(num);

    }

    public void clear() {
        numbers = new ArrayList<T>(maxSize);
    }

    public Double average() {

        double sum = 0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }

        return sum/numbers.size();
    }
}
