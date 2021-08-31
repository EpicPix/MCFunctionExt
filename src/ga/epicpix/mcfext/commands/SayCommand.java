package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;

public final class SayCommand extends Command {

    public SayCommand() {
        super("say", MinecraftVersion.PRE, null);
    }

    public String parse(CommandStringIterator iter, Variables vars) {
        String data = iter.removeNextWhitespace().rest();
        if(data == null || data.trim().isEmpty()) {
            System.out.println("[WARNING] /say has no arguments");
            return null;
        }
        return getName() + " " + vars.placeVariables(data);
    }
}
