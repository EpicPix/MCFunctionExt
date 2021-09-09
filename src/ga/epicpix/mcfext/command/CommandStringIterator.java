package ga.epicpix.mcfext.command;

import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.command.selector.Selector;
import ga.epicpix.mcfext.pos.Position;
import ga.epicpix.mcfext.pos.Vec2d;
import ga.epicpix.mcfext.pos.Vec3d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ga.epicpix.mcfext.Utils.error;

public class CommandStringIterator {

    private final ArrayList<String> lines;
    private int lineIndex = -1;
    private String line;
    private int index;

    public CommandStringIterator(String line) {
        this.lines = new ArrayList<>(Collections.singletonList(line));
        nextLine();
    }

    public CommandStringIterator(List<String> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public boolean hasNextLine() {
        return (lineIndex + 1) < lines.size();
    }

    public CommandStringIterator nextLine() {
        if(hasNextLine()) {
            index = 0;
            line = lines.get(++lineIndex);
        }
        return this;
    }

    public String currentLine() {
        return line;
    }

    public void addLineVariables(Variables vars) {
        lines.set(lineIndex, line = vars.placeVariables(currentLine()));
        index = 0;
    }

    public boolean hasNext() {
        return index < line.length();
    }

    public char seek() {
        if(!hasNext()) return '\0';
        return line.charAt(index);
    }

    public char nextChar() {
        if(!hasNext()) return '\0';
        return line.charAt(index++);
    }

    public char seekBack() {
        return line.charAt(index-2);
    }

    public String nextWord() {
        if(!hasNext()) return null;
        int len = 0, skip = getPosition();
        boolean started = false;
        while(hasNext()) {
            char c = nextChar();
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
        return currentLine().substring(skip, skip + len);
    }

    public Integer nextInt() {
        String word = nextWord();
        try {
            return Integer.decode(word);
        } catch(NumberFormatException e) {
            return null;
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
        if(word == null) return null;
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
        while(index < line.length()) {
            char c = line.charAt(index);
            if(!Character.isWhitespace(c)) {
                break;
            }
            index++;
        }
        return this;
    }

    public String rest() {
        String s = line.substring(index);
        index = line.length();
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
