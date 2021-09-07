package ga.epicpix.mcfext;

import ga.epicpix.mcfext.command.CommandError;

public final class Utils {

    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("DEBUG"));
    private static int errorCount = 0;

    private Utils() {}

    public static void debug(Object msg) {
        if(DEBUG) System.out.println("[DEBUG] " + msg.toString().replace("\n", "\n[DEBUG] "));
    }

    public static void info(Object msg) {
        System.out.println("[INFO] " + msg.toString().replace("\n", "\n[INFO] "));
    }

    public static void warn(Object msg) {
        System.err.println("[WARNING] " + msg.toString().replace("\n", "\n[WARNING] "));
    }

    public static void error(Object msg, boolean count) {
        System.err.println("[ERROR] " + msg.toString().replace("\n", "\n[ERROR] "));
        if(msg instanceof CommandError) {
            if (DEBUG)
                ((CommandError) msg).printStackTrace();
        }
        if(count) errorCount++;
    }

    public static void error(Object msg) {
        error(msg, true);
    }

    public static int getErrorCount() {
        return errorCount;
    }

    public static String repeat(String c, int amt) {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i<amt; i++) {
            out.append(c);
        }
        return out.toString();
    }

}
