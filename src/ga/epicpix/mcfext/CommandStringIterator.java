package ga.epicpix.mcfext;

import static ga.epicpix.mcfext.Utils.error;

public class CommandStringIterator {

    private final String str;
    private int index;

    public CommandStringIterator(String str) {
        this.str = str;
    }

    public String nextWord() {
        if(index >= str.length()) return null;
        int len = 0, skip = index;
        boolean started = false;
        for(int i = index; i<str.length(); i++) {
            char c = str.charAt(i);
            if(!started) {
                if(c == '\n' || c == '\r' || c == '\t' || c == ' ') {
                    skip++;
                }else {
                    started = true;
                }
            }
            if(started) {
                if(c == '\n' || c == '\r' || c == '\t' || c == ' ') {
                    break;
                }
                len++;
            }
        }
        index = skip + len;
        return str.substring(skip, skip + len);
    }

    public int nextInt() {
        String word = nextWord();
        try {
            return Integer.decode(word);
        } catch(NumberFormatException e) {
            error("Not a number");
            return -1;
        }
    }

    public ResourceLocation nextResourceLocation() {
        return new ResourceLocation(nextWord());
    }

    public CommandStringIterator removeNextWhitespace() {
        while(index < str.length()) {
            char c = str.charAt(index);
            if(!(c == '\n' || c == '\r' || c == '\t' || c == ' ')) {
                break;
            }
            index++;
        }
        return this;
    }

    public String rest() {
        String s = str.substring(index);
        index = str.length();
        return s;
    }

    public CommandStringIterator reset() {
        index = 0;
        return this;
    }
}
