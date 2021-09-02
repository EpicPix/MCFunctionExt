package ga.epicpix.mcfext;

import ga.epicpix.mcfext.advancements.AbstractCriterion;
import java.util.ArrayList;

public class Biome implements MCVersionDep, Resource {

    private static final ArrayList<Biome> BIOMES = new ArrayList<>();

    static {
        BIOMES.add(new Biome("minecraft:badlands",                          MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:badlands_plateau",                  MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:bamboo_jungle",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:bamboo_jungle_hills",               MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:basalt_deltas",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:beach",                             MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:birch_forest",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:birch_forest_hills",                MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:cold_ocean",                        MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:crimson_forest",                    MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:dark_forest",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:dark_forest_hills",                 MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:deep_cold_ocean",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:deep_frozen_ocean",                 MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:deep_lukewarm_ocean",               MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:deep_ocean",                        MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:deep_warm_ocean",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:desert",                            MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:desert_hills",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:desert_lakes",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:dripstone_caves",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:end_barrens",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:end_highlands",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:end_midlands",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:eroded_badlands",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:flower_forest",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:forest",                            MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:frozen_ocean",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:frozen_river",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:giant_spruce_taiga",                MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:giant_spruce_taiga_hills",          MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:giant_tree_taiga",                  MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:giant_tree_taiga_hills",            MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:gravelly_mountains",                MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:ice_spikes",                        MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:jungle",                            MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:jungle_edge",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:jungle_hills",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:lukewarm_ocean",                    MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:lush_caves",                        MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:modified_badlands_plateau",         MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:modified_gravelly_mountains",       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:modified_jungle",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:modified_jungle_edge",              MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:modified_wooded_badlands_plateau",  MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:mountain_edge",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:mountains",                         MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:mushroom_field_shore",              MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:mushroom_fields",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:nether_wastes",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:ocean",                             MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:plains",                            MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:river",                             MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:savanna",                           MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:savanna_plateau",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:shattered_savanna",                 MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:shattered_savanna_plateau",         MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:small_end_islands",                 MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:snowy_beach",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:snowy_mountains",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:snowy_taiga",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:snowy_taiga_hills",                 MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:snowy_taiga_mountains",             MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:snowy_tundra",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:soul_sand_valley",                  MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:stone_shore",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:sunflower_plains",                  MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:swamp",                             MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:swamp_hills",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:taiga",                             MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:taiga_hills",                       MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:taiga_mountains",                   MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:tall_birch_forest",                 MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:tall_birch_hills",                  MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:the_end",                           MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:the_void",                          MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:warm_ocean",                        MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:warped_forest",                     MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:wooded_badlands_plateau",           MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:wooded_hills",                      MinecraftVersion.TODO));
        BIOMES.add(new Biome("minecraft:wooded_mountains",                  MinecraftVersion.TODO));
    }

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
