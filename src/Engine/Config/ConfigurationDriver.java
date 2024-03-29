package Engine.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import Engine.ErrorHandler.ErrorHandler;

/**
 * Handle startup tasks in the config file
 */
public class ConfigurationDriver 
{
    ArrayList<String> configLines = new ArrayList<>();

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
            ErrorHandler.handle("Error handled in Engine.ConfigurationDriver.CONSTRUCTOR: \n" + ex.getMessage() );
        }
    }

    /**
     * Mark the database as scaffolded
     */
    public void scaffoldDb()
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
                ErrorHandler.handle("Exception handled in Engine.ConfigurationDriver.scaffoldDb(): \n" + ex.getMessage());
            }
        }
    }

    /**
     * Check if the database has been scaffolded.
     * @return True if database has been scaffolded, false else.
     */
    public boolean isDbScaffolded()
    {
        return configLines.contains("startup_ran : true");
    }    
}