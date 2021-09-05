package ga.epicpix.mcfext;

import ga.epicpix.mcfext.command.CommandError;

public final class Utils {

    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("DEBUG"));
    private static int errorCount = 0;

    private Utils() {}

    public static void debug(Object msg) {
        if(DEBUG) System.out.println("[DEBUG] " + msg);
    }

    public static void info(Object msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void warn(Object msg) {
        System.err.println("[WARNING] " + msg);
    }

    public static void error(Object msg) {
        System.err.println("[ERROR] " + msg);
        if(msg instanceof CommandError) {
            if (DEBUG)
                ((CommandError) msg).printStackTrace();
        }
        errorCount++;
    }

    public static int getErrorCount() {
        return errorCount;
    }
}
