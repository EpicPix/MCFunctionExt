package ga.epicpix.mcfext.command.selector;

import ga.epicpix.mcfext.command.CommandError;
import ga.epicpix.mcfext.command.selector.EntitySelector.TargetSelector;
import ga.epicpix.mcfext.command.CommandStringIterator;

public class Selector {

    public static Object nextSelector(CommandStringIterator iter) {
        iter.removeNextWhitespace();

        StringBuilder temp = new StringBuilder();

        if(iter.seek() != '@') {
            return new StaticSelector(iter.nextWord());
        }else iter.nextChar();
        boolean more = false;
        while(iter.hasNext()) {
            if((iter.seek() == '[' && (more = true)) || (Character.isWhitespace(iter.seek()) && iter.nextChar() != 0)) {
                break;
            }else {
                temp.append(iter.nextChar());
            }
        }

        EntitySelector selector = new EntitySelector(TargetSelector.getTargetSelector(temp.toString()));

        if(selector.getTargetSelector() == null) {
            return new CommandError("Invalid selector: @" + temp);
        }

        if(more) {
            int indent = -1;
            boolean finished = false;
            boolean string = false;
            StringBuilder data = new StringBuilder();
            while(iter.hasNext()) {
                char c = iter.nextChar();
                if(c=='\"') {
                    if(iter.seekBack()!='\\') {
                        string = !string;
                    }
                }

                if(!string) {
                    if(c == '[') {
                        if(indent++ == -1) {
                            continue;
                        }
                    }else if(c == ']') {
                        indent--;
                        if(indent == -1) {
                            finished = true;
                            break;
                        }
                    }
                }

                data.append(c);
            }
            if(!finished) {
                return new CommandError("Selector not finished");
            }
            Object out = selector.apply(data.toString());
            if(out != null) return out;
        }

        return selector;
    }

}
