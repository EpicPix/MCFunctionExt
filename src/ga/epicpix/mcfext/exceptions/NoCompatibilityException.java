package ga.epicpix.mcfext.exceptions;

public class NoCompatibilityException extends RuntimeException {

    public NoCompatibilityException(String command) {
        super(command);
    }

}
