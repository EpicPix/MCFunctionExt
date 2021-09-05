package ga.epicpix.mcfext.command;

public class CommandData {

    private final Command command;
    private final Object[] data;

    private final String errorMessage;

    public CommandData(Command command, Object[] data) {
        this.command = command;
        this.data = data;
        this.errorMessage = null;
    }

    public CommandData(Command command, String errorMessage) {
        this.command = command;
        this.data = null;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(command.toString());
        if(data != null && data.length != 0) {
            for(Object o : data) {
                builder.append(" ").append(o.toString());
            }
        }
        return builder.toString();
    }
}
