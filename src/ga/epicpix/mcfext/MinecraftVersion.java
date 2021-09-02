package ga.epicpix.mcfext;

public enum MinecraftVersion {

    TODO(0, -1),
    PRE(0, -1),
    MC1_12(1, -1),
    MC1_13(2, 4),
    MC1_14(3, 4),
    MC1_15(4, 5),
    MC1_16(5, 6),
    MC1_17(6, 7);

    private final int id;
    private final int packFormat;

    MinecraftVersion(int id, int packFormat) {
        this.id = id;
        this.packFormat = packFormat;
    }

    public int getId() {
        return id;
    }

    public int getPackFormat() {
        return packFormat;
    }
}
