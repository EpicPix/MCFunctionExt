package ga.epicpix.mcfext.command.impl;

import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.command.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

import static ga.epicpix.mcfext.Utils.error;

public class BlockedCommands extends Command {

    public BlockedCommands() {
        super(null, MinecraftVersion.PRE, null, "ban", "ban-ip", "banlist", "debug", "kick", "pardon", "pardon-ip", "perf", "publish", "save-all", "save-off", "save-on", "stop");
    }

    public String parse(String commandName, CommandStringIterator data, Variables vars) {
        error("Using `" + commandName + "` command is not allowed in functions");
        return null;
    }
}
