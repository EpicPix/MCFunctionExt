package ga.epicpix.mcfext.compiler.selectors;

import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.command.CommandStringIterator;
import ga.epicpix.mcfext.datapacks.Datapack;
import ga.epicpix.mcfext.datapacks.DeclaredFunction;
import java.util.ArrayList;

public abstract class ResultSelector {

    public abstract Object handleResult(Command cmd, Datapack pack, DeclaredFunction fun, CommandStringIterator data, Variables vars, ArrayList<Object> vals, String[] args);

}
