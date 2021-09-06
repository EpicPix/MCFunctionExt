package ga.epicpix.mcfext.pos;

import ga.epicpix.mcfext.command.CommandStringIterator;

import static ga.epicpix.mcfext.Utils.error;

public class Vec2d {

    private Position x;
    private Position z;

    public static Vec2d nextVec2d(CommandStringIterator iter) {
        Vec2d vec = new Vec2d();
        vec.x = iter.nextPosition();
        if(vec.x == null) return null;
        vec.z = iter.nextPosition();
        if(vec.z == null) return null;
        if(vec.x.isLocal() || vec.z.isLocal()) {
            error("2d vector cannot have x or z local");
            return null;
        }
        return vec;
    }

    public String toString() {
        return x + " " + z;
    }

}
