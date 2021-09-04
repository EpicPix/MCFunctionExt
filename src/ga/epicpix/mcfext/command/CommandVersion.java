package ga.epicpix.mcfext.command;

import ga.epicpix.mcfext.MCVersionDep;
import ga.epicpix.mcfext.MinecraftVersion;

public class CommandVersion implements MCVersionDep {

    private String added;
    private String removed;

    public MinecraftVersion getAddedVersion() {
        if(added == null) return MinecraftVersion.TODO;
        if(added.contains("."))
            return MinecraftVersion.valueOf(added.replace('.', '_'));
        return MinecraftVersion.valueOf(added);
    }

    public MinecraftVersion getRemovedVersion() {
        if(removed == null) return null;
        if(removed.contains("."))
            return MinecraftVersion.valueOf(removed.replace('.', '_'));
        return MinecraftVersion.valueOf(removed);
    }

}
