package Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ConfigReader 
{

    public static boolean hasStartupRan()
    {
        ArrayList<String> configLines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("config\\config.txt"))))
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

    
}
