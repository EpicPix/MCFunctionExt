package ga.epicpix.mcfext.command;

import ga.epicpix.mcfext.MinecraftVersion;

public class CommandVersion {

    private String added;
    private String removed;

    public MinecraftVersion getAdded() {
        if(added == null) return MinecraftVersion.TODO;
        if(added.contains("."))
            return MinecraftVersion.valueOf(added.replace('.', '_'));
        return MinecraftVersion.valueOf(added);
    }

    public MinecraftVersion getRemoved() {
        if(removed == null) return null;
        if(removed.contains("."))
            return MinecraftVersion.valueOf(removed.replace('.', '_'));
        return MinecraftVersion.valueOf(removed);
    }

}
