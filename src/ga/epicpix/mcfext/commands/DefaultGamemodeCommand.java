package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.Gamemode;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

public class DefaultGamemodeCommand extends Command {

    public DefaultGamemodeCommand() {
        super("defaultgamemode", MinecraftVersion.PRE, null);
    }

    public String parse(String commandName, CommandStringIterator data, Variables vars) {
        return commandName + " " + Gamemode.getCompatible(data.nextWord());
    }
}
