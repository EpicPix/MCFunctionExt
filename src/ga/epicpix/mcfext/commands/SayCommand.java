package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

import static ga.epicpix.mcfext.Utils.warn;

public final class SayCommand extends Command {

    public SayCommand() {
        super("say", MinecraftVersion.PRE, null);
    }

    public String parse(String commandName, CommandStringIterator iter, Variables vars) {
        String data = iter.removeNextWhitespace().rest();
        if(data == null || data.trim().isEmpty()) {
            warn("/say has no arguments");
            return null;
        }
        return getName() + " " + vars.placeVariables(data);
    }
}
