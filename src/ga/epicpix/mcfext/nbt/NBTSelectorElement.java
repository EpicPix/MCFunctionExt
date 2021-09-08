package ga.epicpix.mcfext.nbt;

public class NBTSelectorElement {

    private final String name;
    private final Integer index;

    public NBTSelectorElement(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String toString() {
        return name + (index != null ? "[" + index + "]" : "");
    }
}
