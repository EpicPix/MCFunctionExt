package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

import static ga.epicpix.mcfext.Utils.error;

public class PublishCommand extends Command {

    public PublishCommand() {
        super("publish", MinecraftVersion.PRE, null);
    }

    public String parse(CommandStringIterator data, Variables vars) {
        error("Using `publish` command, they cannot be in functions");
        return null;
    }
}
