package Engine.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import Engine.ErrorHandler.ErrorHandler;

/**
 * File IO with respect to the config file
 */
public class ConfigIO 
{

    /**
     * Check if the startup procedure has been ran
     * @return True if startup procedure has been ran, else false
     */
    public static boolean hasStartupRan()
    {
        ArrayList<String> configLines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("assets\\config.txt"))))
        {
            String currentLine = reader.readLine();

            while(currentLine != null)
            {
                configLines.add(currentLine);
                currentLine = reader.readLine();
            }

            return configLines.contains("startup_ran : true");
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    /**
     * Reset the data in the database from scratch
     */
    public static void reset()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("assets\\config.txt"))))
        {
            writer.write("startup_ran : false");
        }
        catch(Exception ex)
        {
            ErrorHandler.handle(ex.getMessage());
        }
    }
}
