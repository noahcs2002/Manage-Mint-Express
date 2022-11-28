package Graphics;

import javax.swing.*;

import Controllers.SqlController;
import Engine.Config.ConfigIO;
import Engine.ErrorHandler.ErrorHandler;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;
import java.awt.*;
import java.net.URI;
import java.util.ArrayList;

/**
 * Navbar for MME
 */
public class Navbar extends JMenuBar implements ISubscriber, ISubscribable
{
    ArrayList<ISubscriber> subs;
    private String currentTeam;
    
    JMenu help = new JMenu("Help");
    JMenu addPlayer = new JMenu("Add Player");
    JMenu resetMenu = new JMenu("Reset Software");
    JMenu confirmResetMenu = new JMenu("Confirm Reset Software");

    JMenuItem confirmReset = new JMenuItem("Confirm Reset (Irreversible)");

    JMenuItem pitcher = new JMenuItem("Pitcher");
    JMenuItem catcher = new JMenuItem("Catcher");
    JMenuItem infielder = new JMenuItem("Infielder");
    JMenuItem outfielder = new JMenuItem("Outfielder");

    JMenuItem faq = new JMenuItem("FAQ's");
    JMenuItem documentation = new JMenuItem("Docs");
    private final String uri = "https://github.com/noahcs2002/Mangage-Mint-Express";

    public Navbar(String currentTeam)
    {

        this.currentTeam = currentTeam;
        subs = new ArrayList<>();

        confirmResetMenu.add(confirmReset);
        resetMenu.add(confirmResetMenu);

        addPlayer.add(pitcher);
        addPlayer.add(catcher);
        addPlayer.add(infielder);
        addPlayer.add(outfielder);

        help.add(faq);
        help.add(documentation);

        this.add(help);
        this.add(addPlayer);
        this.add(resetMenu);

        faq.addActionListener(e -> 
        {
            new FAQDialog();
        });

        documentation.addActionListener(e -> 
        {
            if(Desktop.isDesktopSupported())
            {
                Desktop desktop = Desktop.getDesktop();
                try
                {
                    desktop.browse(new URI(uri));
                }
                catch(Exception ex)
                {
                    ErrorHandler.handle("EXCEPTION HANDLED BROWSING INTERNET: \n" + ex.getLocalizedMessage());
                }
            }
        });

        pitcher.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Pitchers");
            
            this.subscribe(dialog);

            ErrorHandler.handle(this.currentTeam);
        });

        catcher.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Catchers");
            this.subscribe(dialog);
        });

        infielder.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Infielders");
            this.subscribe(dialog);
        });

        outfielder.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Outfielders");
            this.subscribe(dialog);
        });

        confirmReset.addActionListener(e -> 
        {
            ConfigIO.reset();
            SqlController controller = new SqlController();
            controller.resetDatabase(); 
            this.alert(null, InfoCode.TERMINATE);
        });
    }

    @Override
    public void alert(Object change, InfoCode infoCode) 
    {
        for (ISubscriber sub : subs) 
        {
            sub.getAlert(change, infoCode);    
        }    
    }

    @Override
    public void addSubsriber(ISubscriber subscriber) 
    {
        subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) 
    {
        subs.remove(subscriber);
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        if(infoCode == InfoCode.TEAM_CHANGE)
        {
            this.currentTeam = (String) change;
            ErrorHandler.handle(currentTeam);
        }

        alert(change, infoCode);
    }

    @Override
    public void subscribe(ISubscribable subscribable) 
    {
        subscribable.addSubsriber(this);
    }

    @Override
    public void unsubscribe(ISubscribable subscribable) 
    {
        subscribable.addSubsriber(this);
    }
}
