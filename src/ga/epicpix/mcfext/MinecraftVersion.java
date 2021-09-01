package ga.epicpix.mcfext;

public enum MinecraftVersion {

    TODO(0),
    PRE(0),
    MC1_12(1),
    MC1_13(2),
    MC1_14(3),
    MC1_15(4),
    MC1_16(5),
    MC1_17(6);

    private final int id;

    MinecraftVersion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
