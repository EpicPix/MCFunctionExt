package ga.epicpix.mcfext.advancements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.biomes.Biome;
import ga.epicpix.mcfext.biomes.BiomeType;
import ga.epicpix.mcfext.command.Command;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Advancement implements Resource {

    public static final ArrayList<Advancement> ADVANCEMENTS = new ArrayList<>();

    public static void init() {
        ADVANCEMENTS.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/advancements.json")), new TypeToken<ArrayList<Advancement>>(){}.getType()));
        for(Advancement advancement : ADVANCEMENTS) {
            if(advancement.criteria == null) {
                ArrayList<String> cr = new ArrayList<>();
                cr.add(advancement.getResourceLocation().getName());
                advancement.criteria = cr;
            }
        }
    }

    private String id;
    private Object criteria;

    public static Advancement getAdvancement(ResourceLocation location) {
        for(Advancement adv : ADVANCEMENTS) {
            if(adv.getResourceLocation().getResourceLocation().equals(location.getResourceLocation())) {
                return adv;
            }
        }
        return null;
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(id);
    }

    public String toString() {
        return getResourceLocation().toString();
    }

    public Object getCriterion(String v) {
        ArrayList<Object> crit = new ArrayList<>();
        if(criteria instanceof String) {
            String[] x = ((String) criteria).split(" ");
            if(x[0].equals("@biomes")) {
                String type = x.length != 1 ? x[1] : "all";
                if(type.equals("all")) {
                    crit.addAll(Biome.BIOMES);
                }else {
                    BiomeType t = BiomeType.valueOf(type);
                    for(Biome b : Biome.BIOMES) {
                        if(b.getType() == t) {
                            crit.add(b.getResourceLocation());
                        }
                    }
                }
            }
        }else if(criteria instanceof ArrayList) {
            crit.addAll((ArrayList<Object>) criteria);
        }
        for(Object o : crit) {
            if(o instanceof String) {
                if(o.equals(v)) {
                    return o;
                }
            }else if(o instanceof ResourceLocation) {
                ResourceLocation loc = (ResourceLocation) o;
                if(loc.toString().equals(v) || (loc.getNamespace().equals("minecraft") && loc.getLocation().equals(v))) {
                    return o;
                }
            }
        }
        return null;
    }
}
