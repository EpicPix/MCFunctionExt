package ga.epicpix.mcfext.advancements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.VersionInfo;
import ga.epicpix.mcfext.command.Command;

import java.io.InputStreamReader;
import java.util.ArrayList;

import static ga.epicpix.mcfext.Utils.debug;

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
    private VersionInfo version;
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

    public VersionInfo getVersion() {
        return version;
    }

    public String toString() {
        return getResourceLocation().toString();
    }

    public Object getCriterion(String v) {
        debug("getCriterion(" + v + ")");
        return null;
    }
}
