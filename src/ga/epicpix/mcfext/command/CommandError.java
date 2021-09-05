package ga.epicpix.mcfext.command;

public class CommandError {

    private final String message;
    private final StackTraceElement[] stack;

    public CommandError(String message) {
        this.message = message;
        stack = new Exception().getStackTrace();
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return getMessage();
    }

    public void printStackTrace() {
        for(int i = 1; i < stack.length; i++) {
            System.err.println("\tat " + stack[i]);
        }
    }

}
