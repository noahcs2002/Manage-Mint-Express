package Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Engine.ErrorHandler.ErrorHandler;

public class SqlUtilityTool
{
    public static String extractSqlStringFromFile(File file) throws FileNotFoundException
    {
        String result = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
          String currentLine = reader.readLine();
          while(currentLine != null)
          {
            result += currentLine;
            result += "\n";
            currentLine = reader.readLine();
          }

          return result;
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("<DEBUG>: Exception Handled in Engine.SqlUtilityTool.java:\n" + ex.getMessage());
            return ex.getMessage();
        }
    }

    
}
