package ga.epicpix.mcfext;

import static ga.epicpix.mcfext.MinecraftVersion.*;

import java.util.ArrayList;

public class Advancement implements Resource, MCVersionDep {

    public static final ArrayList<Advancement> ADVANCEMENTS = new ArrayList<>();

    public static void init() {
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/adventuring_time", TODO, Biome.asCriteria()));
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/arbalistic", TODO, "arbalistic"));
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/bullseye", TODO, "bullseye"));
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/hero_of_the_village", TODO, "hero_of_the_village"));
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/honey_block_slide", TODO, "honey_block_slide"));
    }

    private final ResourceLocation loc;
    private final MinecraftVersion added;
    private final MinecraftVersion removed;
    private final AbstractCriterion[] criteria;

    public Advancement(String res, MinecraftVersion added, AbstractCriterion... criteria) {
        loc = new ResourceLocation(res);
        this.added = added;
        this.removed = null;
        this.criteria = criteria;
    }

    public Advancement(String res, MinecraftVersion added, String... sCriteria) {
        loc = new ResourceLocation(res);
        AbstractCriterion[] criteria = new AbstractCriterion[sCriteria.length];
        for(int i = 0; i<sCriteria.length; i++) {
            criteria[i] = new Criterion(sCriteria[i], added);
        }
        this.added = added;
        this.removed = null;
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

    public MinecraftVersion getAddedVersion() {
        return added;
    }

    public MinecraftVersion getRemovedVersion() {
        return removed;
    }
}
