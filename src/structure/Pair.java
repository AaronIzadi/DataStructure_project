package structure;

import java.util.Objects;

public class Pair<T, K> {
    private final T firstObject;
    private final K secondObject;

    public Pair(T firstObject, K secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public T getFirstObject() {
        return firstObject;
    }

    public K getSecondObject() {
        return secondObject;
    }


    @Override
    public String toString() {
        return "Pair{" +
                "firstObject=" + firstObject +
                ", secondObject=" + secondObject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<T, K> pair = (Pair<T, K>) o;
        return firstObject.equals(pair.firstObject) &&
                secondObject.equals(pair.secondObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstObject, secondObject);
    }

}
