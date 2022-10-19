package Engine.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ConfigIO 
{

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

    public static void reset()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("assets\\config.txt"))))
        {
            writer.write("startup_ran : false");
        }
        catch(Exception ex)
        {
        }
    }

    
}
