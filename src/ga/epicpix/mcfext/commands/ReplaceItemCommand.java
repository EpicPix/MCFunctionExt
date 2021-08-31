package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

public class ReplaceItemCommand extends Command {

    public ReplaceItemCommand() {
        super("replaceitem", MinecraftVersion.PRE, MinecraftVersion.MC1_17);
    }

    public String compatibility(CommandStringIterator data, MinecraftVersion version, Variables vars) {
        return "item replace" + data.rest();
    }

    public String parse(CommandStringIterator data, Variables vars) {
        return super.parse(data, vars);
    }
}
