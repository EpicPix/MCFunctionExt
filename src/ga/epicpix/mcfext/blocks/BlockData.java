package ga.epicpix.mcfext.blocks;

public enum BlockData {

    POWERED(0, "false", "true"),
    FACING(1, "east", "north", "south", "west"),
    FACE(2, "ceiling", "floor", "wall"),
    OPEN(3, "false", "true"),
    HALF(4, "lower", "upper"),
    HINGE(5, "left", "right"),
    WATERLOGGED(6, "false", "true"),
    EAST(7, "false", "true"),
    NORTH(8, "false", "true"),
    SOUTH(9, "false", "true"),
    WEST(10, "false", "true"),
    IN_WALL(11, "false", "true"),
    DISTANCE(12, "1", "2", "3", "4", "5", "6", "7"),
    PERSISTENT(13, "false", "true"),
    AXIS(14, "x", "y", "z"),
    STAGE(15, "0", "1"),
    ROTATION(16, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"),
    TYPE(17, "bottom", "double", "top"),
    HALF2(18, "bottom", "top"),
    SHAPE(19, "inner_left", "inner_right", "outer_left", "outer_right", "straight"),

    ;

    private final long hex;
    private final String[] allowed;

    BlockData(int bit, String... properties) {
        hex = 1L << bit;
        allowed = properties;
    }

    public long getHex() {
        return hex;
    }
}
