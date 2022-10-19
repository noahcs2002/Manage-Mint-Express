package Engine.ErrorHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

/**
 * Custom exception and error handler.
 */
public final class ErrorHandler
{
    private static final String path = "assets\\error.log.txt";
    
    /**
     * Document an exception or error.
     * @param exception Exception to document
     */
    public static void handle(final String exception)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), true)))
        {
            writer.write(exception + "\n");
            writer.flush();
            writer.close();
        } 
        catch(Exception ex){}
    }
}
