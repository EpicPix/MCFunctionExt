package ga.epicpix.mcfext.nbt;

public class NBTPathElement {

    private final String name;
    private final Integer index;

    public NBTPathElement(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String toString() {
        return name + (index != null ? "[" + index + "]" : "");
    }
}
