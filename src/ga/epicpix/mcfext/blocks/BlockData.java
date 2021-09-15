package ga.epicpix.mcfext.blocks;

public enum BlockData {

    POWERED("powered", 0, "false", "true"),
    FACING("facing", 1, "east", "north", "south", "west"),
    FACE("face", 2, "ceiling", "floor", "wall"),
    OPEN("open", 3, "false", "true"),
    PART("half", 4, "lower", "upper"),
    HINGE("hinge", 5, "left", "right"),
    WATERLOGGED("waterlogged", 6, "false", "true"),
    EAST("east", 7, "false", "true"),
    NORTH("north", 8, "false", "true"),
    SOUTH("south", 9, "false", "true"),
    WEST("west", 10, "false", "true"),
    IN_WALL("in_wall", 11, "false", "true"),
    DISTANCE("distance", 12, "1", "2", "3", "4", "5", "6", "7"),
    PERSISTENT("persistent", 13, "false", "true"),
    AXIS("axis", 14, "x", "y", "z"),
    STAGE("stage", 15, "0", "1"),
    ROTATION("rotation", 16, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"),
    TYPE("type", 17, "bottom", "double", "top"),
    HALF2("half", 18, "bottom", "top"),
    SHAPE("shape", 19, "inner_left", "inner_right", "outer_left", "outer_right", "straight"),
    RAIL_SHAPE("shape", 20, "ascending_east", "ascending_north", "ascending_south", "ascending_west", "east_west", "north_south"),
    UP("up", 21, "false", "true"),

    ;

    private final long hex;
    private final String[] allowed;

    BlockData(String name, int bit, String... properties) {
        hex = 1L << bit;
        allowed = properties;
    }

    public long getHex() {
        return hex;
    }
}
