package Engine;
import Graphics.*;
import Graphics.Startup.StartupDialog;
import java.awt.event.*;

import Controllers.SqlController;

/**
 * Main Driver class for Manage-Mint Express
 */
public class Driver
{
    public static void main(String[] args) 
    {
        if(!ConfigReader.hasStartupRan())
        {
            StartupDialog d = new StartupDialog();

            d.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent we)
                {
                    new Startup(d.team);
                    new FancyFrame(d.team);
                }
            });
        }

        else
        {
            new FancyFrame(SqlController.getTeam());
        }
        
    }
}