package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

public class BlockDataCommand extends Command {

    public BlockDataCommand() {
        super("blockdata", MinecraftVersion.PRE, MinecraftVersion.MC1_13);
    }

    public String compatibility(String commandName, CommandStringIterator data, MinecraftVersion version, Variables vars) {
        return "data merge block " + data.nextWord() + " " + data.nextWord() + " " + data.nextWord() + " " + data.rest();
    }
}
