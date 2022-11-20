package Engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Controllers.SqlController;
import Engine.Config.ConfigIO;
import Engine.ErrorHandler.ErrorHandler;
import Graphics.FancyFrame;
import Graphics.Loading;
import Graphics.StartupDialog;

/**
 * Main Driver class for Manage-Mint Express
 */
public class Driver
{
    public static void main(String[] args) 
    {
        // If config hasn't ran, run it, and when it's done, start program
        if(!ConfigIO.hasStartupRan())
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
            try
            {
                Loading l = new Loading();
                l.loading();
                new FancyFrame(SqlController.getTeam());
                l.dispose();
            }
            catch(Exception ex)
            {
                ErrorHandler.handle(ex.getMessage());
                System.out.println("Fatal Error Occurred, please see error log for more details.");
            }
        }
    }
}