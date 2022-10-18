package Engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ConfigurationDriver 
{
    ArrayList<String> configLines = new ArrayList<>();

    public ConfigurationDriver()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("config\\config.txt"))))
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
            System.out.println("Error handled in Engine.ConfigurationDriver.CONSTRUCTOR: \n" + ex.getMessage() );
        }
    }

    public void scaffoldDb()
    {

        if(!configLines.contains("startup_ran : true") &&
           !configLines.contains("startup_ran : false")) 
        return ;
        
        if(configLines.contains("startup_ran : false"))
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("config\\config.txt"))))
            {
                writer.write("startup_ran : true");
            }
            catch(Exception ex)
            {
                System.out.println("Exception handled in Engine.ConfigurationDriver.scaffoldDb(): \n" + ex.getMessage());
            }
        }
    }

    public boolean isDbScaffolded()
    {
        return configLines.contains("startup_ran : true");
    }    
}