package ga.epicpix.mcfext.pos;

import ga.epicpix.mcfext.command.CommandStringIterator;

public class Position {

    private boolean relative;
    private boolean local;
    private double location;

    public static Position nextPosition(CommandStringIterator iter) {
        String w = iter.nextWord();
        if(w == null) return null;
        Position pos = new Position();
        if(w.startsWith("^")) {
            pos.local = true;
            w = w.substring(1);
        }else if(w.startsWith("~")) {
            pos.relative = true;
            w = w.substring(1);
        }
        if(w.isEmpty()) return pos;
        try {
            double d = Double.parseDouble(w);
            if ((int) d == d) {
                boolean addp5 = !w.endsWith(".0");
                if (addp5) {
                    d += 0.5;
                }
            }
            pos.location = d;
        }catch(NumberFormatException e) {
            return null;
        }
        return pos;
    }

    public boolean isLocal() {
        return local;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(relative) builder.append("~");
        if(local) builder.append("^");
        if(location != 0)
            builder.append(location);
        return builder.toString();
    }
}
