package Engine.ErrorHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public final class ErrorHandler
{
    private static final String path = "assets\\error.log.txt";
    
    public static void handle(final String exception)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), true)))
        {
            writer.write(exception + "\n\n");
            writer.flush();
            writer.close();
        } 
        catch(Exception ex){}
    }
}
