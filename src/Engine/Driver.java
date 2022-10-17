package Engine;
import Graphics.*;

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
            try
            {
                Loading l = new Loading();
                // l.loading();
                new FancyFrame(SqlController.getTeam());
                l.dispose();
            }
            catch(Exception ex)
            {

            }
        }
        
    }
}