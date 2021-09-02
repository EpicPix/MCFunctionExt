package ga.epicpix.mcfext;

public class StaticSelector extends Selector {

    private final String name;

    public StaticSelector(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

}
