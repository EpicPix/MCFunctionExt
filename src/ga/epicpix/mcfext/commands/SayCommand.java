package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.MinecraftVersion;

public final class SayCommand extends Command {

    public SayCommand() {
        super("say", MinecraftVersion.PRE, null);
    }

    public String parse(String data) {
        if(data == null || data.trim().isEmpty()) {
            System.out.println("[WARNING] /say has no arguments");
            return null;
        }
        return super.parse(data);
    }
}
