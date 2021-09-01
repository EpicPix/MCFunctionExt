package ga.epicpix.mcfext;

public class Criterion extends AbstractCriterion {

    private final MinecraftVersion added;
    private final MinecraftVersion removed;

    public Criterion(String name, MinecraftVersion added) {
        super(name);
        this.added = added;
        removed = null;
    }

    public Criterion(ResourceLocation location, MinecraftVersion added) {
        super(location);
        this.added = added;
        removed = null;
    }

    public Criterion(String name, MinecraftVersion added, MinecraftVersion removed) {
        super(name);
        this.added = added;
        this.removed = removed;
    }

    public Criterion(ResourceLocation location, MinecraftVersion added, MinecraftVersion removed) {
        super(location);
        this.added = added;
        this.removed = removed;
    }

    public MinecraftVersion getAddedVersion() {
        return added;
    }

    public MinecraftVersion getRemovedVersion() {
        return removed;
    }
}
