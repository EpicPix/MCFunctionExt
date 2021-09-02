package ga.epicpix.mcfext.advancements;

import ga.epicpix.mcfext.MCVersionDep;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.ResourceLocation;
import java.util.ArrayList;

public abstract class AbstractCriterion implements MCVersionDep {

    private String name;
    private ResourceLocation location;

    public AbstractCriterion(String name) {
        this.name = name;
    }

    public AbstractCriterion(ResourceLocation location) {
        this.location = location;
    }

    public String[] getNames() {
        ArrayList<String> str = new ArrayList<>();
        if(name != null) str.add(name);
        if(location != null) {
            str.add(location.getResourceLocation());
            if(location.getNamespace().equals("minecraft")) {
                str.add(location.getLocation());
            }
        }
        return str.toArray(new String[0]);
    }

    public abstract MinecraftVersion getAddedVersion();
    public abstract MinecraftVersion getRemovedVersion();
}
