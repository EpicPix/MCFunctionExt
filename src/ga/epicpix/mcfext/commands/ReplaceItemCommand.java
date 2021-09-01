package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

public class ReplaceItemCommand extends Command {

    public ReplaceItemCommand() {
        super("replaceitem", MinecraftVersion.PRE, MinecraftVersion.MC1_17);
    }

    public String compatibility(String commandName, CommandStringIterator data, MinecraftVersion version, Variables vars) {
        return "item replace " + data.rest();
    }

    public String parse(String commandName, CommandStringIterator data, Variables vars) {
        return super.parse(commandName, data, vars);
    }
}
