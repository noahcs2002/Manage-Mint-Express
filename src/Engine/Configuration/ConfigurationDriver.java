package Engine.Configuration;

import Engine.ErrorHandler.ErrorHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public final class ConfigurationDriver 
{
    private static ArrayList<String> configLines = new ArrayList<>();

    public ConfigurationDriver()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("assets\\config.txt"))))
        {
            String currentLine = reader.readLine();

            while(currentLine != null)
            {
                configLines.add(currentLine);
                currentLine = reader.readLine();
            }
        }
        catch(Exception ex)
        {
            ErrorHandler.handle(ex.getMessage());
        }
    }

    public static void scaffoldDb()
    {

        if(!configLines.contains("startup_ran : true") &&
           !configLines.contains("startup_ran : false")) 
        return ;
        
        if(configLines.contains("startup_ran : false"))
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("assets\\config.txt"))))
            {
                writer.write("startup_ran : true");
            }
            catch(Exception ex)
            {
                ErrorHandler.handle(ex.getMessage());
            }
        }
    }

    public static boolean isDbScaffolded()
    {
        try
        {
            return configLines.contains("startup_ran : true");
        }
        catch(Exception ex)
        {
            ErrorHandler.handle(ex.getMessage());
            return false;
        }
    }    

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
            ErrorHandler.handle(ex.getMessage());
            return false;
        }
    }

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