package Graphics;

import javax.swing.*;

import Engine.ConfigIO;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;
import java.awt.*;
import java.net.URI;
import java.util.ArrayList;

public class Navbar extends JMenuBar implements ISubscriber, ISubscribable
{
    ArrayList<ISubscriber> subs;
    private String currentTeam;
    
    JMenu help = new JMenu("Help");
    JMenu addPlayer = new JMenu("Add Player");
    JMenu gameDayMenu = new JMenu("Enter Game Day");
    JMenu resetMenu = new JMenu("Reset");
    JMenu confirmResetMenu = new JMenu("Confirm Reset");

    JMenuItem confirmReset = new JMenuItem("Confirm Reset (Irreversible)");

    JMenuItem pastGames = new JMenuItem("Past Games");
    JMenuItem upcomingGames = new JMenuItem("Upcoming Games");

    JMenuItem pitcher = new JMenuItem("Pitcher");
    JMenuItem catcher = new JMenuItem("Catcher");
    JMenuItem infielder = new JMenuItem("Infielder");
    JMenuItem outfielder = new JMenuItem("Outfielder");

    JMenuItem faq = new JMenuItem("FAQ's");
    JMenuItem howToUse = new JMenuItem("How to use");
    JMenuItem documentation = new JMenuItem("Docs");
    private final String uri = "https://github.com/noahcs2002/Mangage-Mint-Express";

    public Navbar(String currentTeam)
    {

        this.currentTeam = currentTeam;
        subs = new ArrayList<>();

        confirmResetMenu.add(confirmReset);
        resetMenu.add(confirmResetMenu);


        gameDayMenu.add(pastGames);
        gameDayMenu.add(upcomingGames);

        addPlayer.add(pitcher);
        addPlayer.add(catcher);
        addPlayer.add(outfielder);
        addPlayer.add(infielder);

        help.add(faq);
        help.add(howToUse);
        help.add(documentation);

        this.add(help);
        this.add(addPlayer);
        this.add(gameDayMenu);
        this.add(resetMenu);

        faq.addActionListener(e -> 
        {
            JDialog faqDialog = new JDialog();
            faqDialog.setTitle("FAQ's");

            final String[] faqs = 
            {
                "Q. Who developed this? A. Noah Sternberg, a student at Murray State University.",
                "Q. What was this developed for? A. A final project for an Advance Objects class.",
                "Q. How long did this take? A. Too long.",
                "Q. Will this ever get more features? A. I don't know.",
                "Q. Why did you make ___ design choice? A. Because I'm bad at graphic design.",
            };

            JList<String> list = new JList<>(faqs);

            faqDialog.add(list);

            faqDialog.setLocationRelativeTo(null);
            faqDialog.setSize(500,150);
            faqDialog.setVisible(true);
        });

        howToUse.addActionListener(e -> 
        {

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
                    System.out.println("EXCEPTION HANDLED BROWSING INTERNET: \n" + ex.getLocalizedMessage());
                }
            }
        });

        pitcher.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Pitchers");
            
            this.subscribe(dialog);

            System.out.println(this.currentTeam);
        });

        catcher.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Catchers");
            
            this.subscribe(dialog);

            System.out.println(this.currentTeam);
        });

        infielder.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Infielders");
            
            this.subscribe(dialog);

            System.out.println(this.currentTeam);
        });

        outfielder.addActionListener(e -> 
        {
            AddPlayerDialog dialog = new AddPlayerDialog(this.currentTeam, "Outfielders");
            this.subscribe(dialog);

            System.out.println(this.currentTeam);
        });

        pastGames.addActionListener(e -> 
        {
            new PastGamesDialog();
        });

        upcomingGames.addActionListener(e ->
        {
            new UpcomingGamesDialog();
        });

        confirmReset.addActionListener(e -> 
        {
            ConfigIO.reset();
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
            System.out.println(currentTeam);
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
