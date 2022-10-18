package Engine.SQL;

import Engine.ErrorHandler.ErrorHandler;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

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
				result += currentLine + ":\n";
				currentLine = reader.readLine();
			}

			return result;
		}
		catch(Exception ex)
		{
            ErrorHandler.handle(ex.getMessage());
			return "";
		}
	}
}
