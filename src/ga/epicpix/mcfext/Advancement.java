package ga.epicpix.mcfext;

import java.util.ArrayList;

public class Advancement implements Resource {

    public static final ArrayList<Advancement> ADVANCEMENTS = new ArrayList<>();

    public static void init() {
        ADVANCEMENTS.add(new Advancement("minecraft:adventure/adventuring_time", Biome.asCriteria()));
    }

    private final ResourceLocation loc;
    private final Criterion[] criteria;

    public Advancement(String res, Criterion[] criteria) {
        loc = new ResourceLocation(res);
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

    public Criterion[] getCriteria() {
        return criteria;
    }

    public Criterion getCriterion(String name) {
        for(Criterion criterion : criteria) {
            for(String str : criterion.getNames()) {
                if(name.equals(str)) {
                    return criterion;
                }
            }
        }
        return null;
    }
}
