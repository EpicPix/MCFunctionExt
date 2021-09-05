package ga.epicpix.mcfext;

import ga.epicpix.mcfext.advancements.AbstractCriterion;
import java.util.ArrayList;

public class Biome implements MCVersionDep, Resource {

    private static final ArrayList<Biome> BIOMES = new ArrayList<>();

    public static Biome[] values() {
        return BIOMES.toArray(new Biome[0]);
    }

    public static AbstractCriterion[] asCriteria() {
        AbstractCriterion[] criteria = new AbstractCriterion[BIOMES.size()];
        for(int i = 0; i<criteria.length; i++) {
            Biome biome = BIOMES.get(i);
            criteria[i] = new AbstractCriterion(biome.loc) {
                public MinecraftVersion getAddedVersion() {
                    return biome.getAddedVersion();
                }

                public MinecraftVersion getRemovedVersion() {
                    return biome.getRemovedVersion();
                }
            };
        }
        return criteria;
    }

    private final ResourceLocation loc;
    private final MinecraftVersion added;

    private final MinecraftVersion removed;

    public Biome(String id, MinecraftVersion added) {
        this(id, added, null);
    }

    public Biome(String id, MinecraftVersion added, MinecraftVersion removed) {
        this.loc = new ResourceLocation(id);
        this.added = added;
        this.removed = removed;
        BIOMES.add(this);
    }

    public ResourceLocation getResourceLocation() {
        return loc;
    }

    public MinecraftVersion getAddedVersion() {
        return added;
    }

    public MinecraftVersion getRemovedVersion() {
        return removed;
    }
}
