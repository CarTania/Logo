package cartechinitania115422;

/**
 * Eccezione che viene lanciata nel caso che il comando nel file logo non viene trovato.
 */
public class CommandNotFoundException extends Exception {
    
    public CommandNotFoundException()
    {
        super();
    }

    public CommandNotFoundException(String message)
    {
        super(message);
    }
}
