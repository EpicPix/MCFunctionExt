package ga.epicpix.mcfext;

import java.util.ArrayList;

public abstract class Criterion implements MCVersionDep {

    private String name;
    private ResourceLocation location;

    public Criterion(String name) {
        this.name = name;
    }

    public Criterion(ResourceLocation location) {
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
