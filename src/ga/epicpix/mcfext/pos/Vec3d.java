package ga.epicpix.mcfext.pos;

import ga.epicpix.mcfext.command.CommandStringIterator;

import static ga.epicpix.mcfext.Utils.error;

public class Vec3d {

    private Position x;
    private Position y;
    private Position z;

    public static Vec3d nextVec3d(CommandStringIterator iter) {
        Vec3d vec = new Vec3d();
        vec.x = iter.nextPosition();
        if(vec.x == null) return null;
        vec.y = iter.nextPosition();
        if(vec.y == null) return null;
        vec.z = iter.nextPosition();
        if(vec.z == null) return null;
        if(vec.x.isLocal() != vec.y.isLocal() || vec.y.isLocal() != vec.z.isLocal()) {
            error("Mixing local and non local positions");
            return null;
        }
        return vec;
    }

    public String toString() {
        return x + " " + y + " " + z;
    }
}
