package Engine;

import Engine.Configuration.ConfigurationDriver;
import Engine.ErrorHandler.ErrorHandler;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Engine.SQL.SqlController;
import Graphics.StartupDialog;
import Graphics.FancyFrame;
import Graphics.Loading;

/**
 * Main Driver class for Manage-Mint Express
 */
public class Driver
{
    public static void main(String[] args) 
    {
        if(!ConfigurationDriver.hasStartupRan())
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
            }
        }
        
    }
}