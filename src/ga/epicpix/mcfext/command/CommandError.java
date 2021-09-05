package ga.epicpix.mcfext.command;

public class CommandError {

    private final String message;

    public CommandError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
