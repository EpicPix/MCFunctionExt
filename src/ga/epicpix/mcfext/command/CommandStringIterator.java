package ga.epicpix.mcfext.command;

import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.command.selector.Selector;
import ga.epicpix.mcfext.pos.Position;
import ga.epicpix.mcfext.pos.Vec2d;
import ga.epicpix.mcfext.pos.Vec3d;

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

    public ResourceLocation nextResourceLocation(String namespace) {
        return new ResourceLocation(null, nextWord(), namespace);
    }

    public Selector nextSelector() {
        return Selector.nextSelector(this);
    }

    public Position nextPosition() {
        return Position.nextPosition(this);
    }

    public Vec2d nextVec2d() {
        return Vec2d.nextVec2d(this);
    }

    public Vec3d nextVec3d() {
        return Vec3d.nextVec3d(this);
    }

    public Long nextTime() {
        String word = nextWord();
        String unit = "t";
        if(word.endsWith("t") || word.endsWith("s") || word.endsWith("d")) {
            unit = word.substring(word.length() - 1);
            word = word.substring(0, word.length() - 1);
        }
        if(word.startsWith(".")) word = "0" + word;
        try {
            double d = Double.parseDouble(word);
            if (unit.equals("t")) {
                return (long) d;
            } else if (unit.equals("s")) {
                return (long) (d * 20);
            } else if (unit.equals("d")) {
                return (long) (d * 24000);
            } else {
                return null;
            }
        }catch(NumberFormatException e) {
            return null;
        }
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

    public String restW() {
        return removeNextWhitespace().rest();
    }

    public CommandStringIterator reset() {
        index = 0;
        return this;
    }

    public int getPosition() {
        return index;
    }

    public void setPosition(int pos) {
        index = pos;
    }
}
