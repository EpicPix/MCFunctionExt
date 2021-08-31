package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.MinecraftVersion;

public final class SayCommand extends Command {

    public SayCommand() {
        super("say", true, MinecraftVersion.PRE, null);
    }

}
