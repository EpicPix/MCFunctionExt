package ga.epicpix.mcfext.compiler.selectors.result;

import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.command.CommandData;
import ga.epicpix.mcfext.command.CommandError;
import ga.epicpix.mcfext.command.CommandStringIterator;
import ga.epicpix.mcfext.compiler.selectors.ResultSelector;
import ga.epicpix.mcfext.datapacks.Datapack;
import ga.epicpix.mcfext.datapacks.DeclaredFunction;
import java.util.ArrayList;
import java.util.Arrays;

public class command extends ResultSelector {

    public Object handleResult(Command cmd, Datapack pack, DeclaredFunction fun, CommandStringIterator data, Variables vars, ArrayList<Object> vals, String[] args) {
        String sarg1 = args.length > 1 ? args[1] : null;
        int back = data.getPosition();
        Command command = Command.getCommand(sarg1);
        if(sarg1 == null) {
            String cmdname = data.nextWord();
            vals.add(cmdname);
            command = Command.getCommand(cmdname);

            String cname = cmdname + (command == null ? "?" : "");
            Integer current = Command.commandUsage.get(cname);
            if(current==null) current = 0;
            Command.commandUsage.put(cname, current + 1);
        }
        if(command == null) {
            data.setPosition(back);
            return new CommandError("Unknown command");
        }
        Object parsed = command.parse(pack, fun, data, vars);
        if(parsed == null || parsed instanceof CommandError) {
            return parsed;
        }
        vals.addAll(Arrays.asList(((CommandData) parsed).getData()));
        return vals;
    }

}
