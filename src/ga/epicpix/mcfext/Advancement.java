package ga.epicpix.mcfext;

import java.util.ArrayList;

public class Advancement implements Resource {

    public static final ArrayList<Advancement> ADVANCEMENTS = new ArrayList<>();

    public static void init() {
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/adventuring_time", Biome.asCriteria()));
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/arbalistic", "arbalistic"));
    }

    private final ResourceLocation loc;
    private final AbstractCriterion[] criteria;

    public Advancement(String res, AbstractCriterion... criteria) {
        loc = new ResourceLocation(res);
        this.criteria = criteria;
    }

    public Advancement(String res, String... sCriteria) {
        loc = new ResourceLocation(res);
        AbstractCriterion[] criteria = new AbstractCriterion[sCriteria.length];
        for(int i = 0; i<sCriteria.length; i++) {
            criteria[i] = new AbstractCriterion(sCriteria[i]) {
                public MinecraftVersion getAddedVersion() {
                    return MinecraftVersion.MC1_12; // Version where advancements were added
                }

                public MinecraftVersion getRemovedVersion() {
                    return null;
                }
            };
        }
        this.criteria = criteria;
    }

    public static Advancement getAdvancement(ResourceLocation location) {
        for(Advancement adv : ADVANCEMENTS) {
            if(adv.getResourceLocation().getResourceLocation().equals(location.getResourceLocation())) {
                return adv;
            }
        }
        return null;
    }

    public ResourceLocation getResourceLocation() {
        return loc;
    }

    public AbstractCriterion[] getCriteria() {
        return criteria;
    }

    public AbstractCriterion getCriterion(String name) {
        for(AbstractCriterion criterion : criteria) {
            for(String str : criterion.getNames()) {
                if(name.equals(str)) {
                    return criterion;
                }
            }
        }
        return null;
    }
}
