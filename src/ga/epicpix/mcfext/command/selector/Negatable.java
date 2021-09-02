package ga.epicpix.mcfext.command.selector;

public class Negatable<T> {

    private T data;
    private boolean negated;

    public Negatable() {}

    public Negatable(T data) {
        if(data == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.data = data;
    }

    public void set(T data, boolean negated) {
        if(!(this.data == null || this.data == data)) {
            throw new IllegalArgumentException("Cannot change data");
        }
        this.negated = negated;
    }

    public T get() {
        return data;
    }

    public boolean isNegated() {
        return negated;
    }

    public void setNegated(boolean neg) {
        negated = neg;
    }

    public String toString() {
        return (negated ? "!" : "") + data;
    }
}
