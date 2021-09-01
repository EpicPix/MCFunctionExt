package ga.epicpix.mcfext;

import static ga.epicpix.mcfext.Utils.error;

public class CommandStringIterator {

    private final String str;
    private int index;

    public CommandStringIterator(String str) {
        this.str = str;
    }

    public boolean hasNext() {
        return index < str.length();
    }

    public char seek() {
        if(!hasNext()) return '\0';
        return str.charAt(index);
    }

    public char nextChar() {
        if(!hasNext()) return '\0';
        return str.charAt(index++);
    }

    public char seekBack() {
        if(!hasNext()) return '\0';
        return str.charAt(index-2);
    }

    public String nextWord() {
        if(!hasNext()) return null;
        int len = 0, skip = index;
        boolean started = false;
        for(int i = index; i<str.length(); i++) {
            char c = str.charAt(i);
            if(!started) {
                if(Character.isWhitespace(c)) {
                    skip++;
                }else {
                    started = true;
                }
            }
            if(started) {
                if(Character.isWhitespace(c)) {
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

    public Selector nextSelector() {
        return Selector.nextSelector(this);
    }

    public CommandStringIterator removeNextWhitespace() {
        while(index < str.length()) {
            char c = str.charAt(index);
            if(!Character.isWhitespace(c)) {
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
