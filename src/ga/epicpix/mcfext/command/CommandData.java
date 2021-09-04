package ga.epicpix.mcfext.command;

public class CommandData {

    private final Command command;
    private final Object[] data;

    public CommandData(Command command, Object[] data) {
        this.command = command;
        this.data = data;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(command.toString());
        if(data.length != 0) {
            for(Object o : data) {
                builder.append(" ").append(o.toString());
            }
        }
        return builder.toString();
    }
}
