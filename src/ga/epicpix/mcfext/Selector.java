package ga.epicpix.mcfext;

import ga.epicpix.mcfext.EntitySelector.TargetSelector;

import static ga.epicpix.mcfext.Utils.error;

public class Selector {

    public static Selector nextSelector(CommandStringIterator iter) {
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
            error("Invalid selector: @" + temp);
            return null;
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
                error("Selector not finished");
            }
            selector.apply(data.toString());
        }

        return selector;
    }

}
