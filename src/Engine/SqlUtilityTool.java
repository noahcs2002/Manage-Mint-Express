package Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Engine.ErrorHandler.ErrorHandler;

/**
 * Custom SQL utility tool that extracts SQL files and turns them into SqlStrings to 
 * be parsed through the JDBC driver. I will most likely make this more formal as my 
 * next project.
 */
public class SqlUtilityTool
{
  /**
   * 
   * @param file File to parse into SqlString
   * @return SqlString representation of an SQL file
   * @throws FileNotFoundException File Not Found -> Exception to be handled
   */
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
