package Graphics;

import javax.swing.*;

import Interfaces.ISubscribable;
import Interfaces.ISubscriber;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class Navbar extends JMenuBar implements ISubscribable, ISubscriber
{
    ArrayList<ISubscriber> subs;
    private String currentTeam;
    
    JMenu help = new JMenu("Help");
    JMenu addPlayer = new JMenu("Add Player");
    JMenuItem addTeam = new JMenuItem("Add Team");

    JMenuItem pitcher = new JMenuItem("Pitcher");
    JMenuItem catcher = new JMenuItem("Catcher");
    JMenuItem infielder = new JMenuItem("Infielder");
    JMenuItem outfielder = new JMenuItem("Outfielder");

    
    

    JMenuItem faq = new JMenuItem("FAQ's");
    JMenuItem howToUse = new JMenuItem("How to use");
    JMenuItem documentation = new JMenuItem("Docs");
    private String uri = "https://github.com/noahcs2002/Mangage-Mint-Express";

    public Navbar(String currentTeam)
    {
        this.currentTeam = currentTeam;
        subs = new ArrayList<>();

        addPlayer.add(pitcher);
        addPlayer.add(catcher);
        addPlayer.add(outfielder);
        addPlayer.add(infielder);

        help.add(faq);
        help.add(howToUse);
        help.add(documentation);

        this.add(help);
        this.add(addPlayer);
        this.add(addTeam);

        addTeam.addActionListener(e -> 
        {   
            new AddTeamDialog(this);
        });

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
            new AddPlayerDialog(  this.currentTeam, "Pitchers", this);
            System.out.println(this.currentTeam);
        });

        catcher.addActionListener(e -> 
        {
            new AddPlayerDialog(this.currentTeam, "Catchers", this);
            System.out.println(this.currentTeam);
        });

        infielder.addActionListener(e -> 
        {
            new AddPlayerDialog(this.currentTeam, "Infielders", this);
            System.out.println(this.currentTeam);
        });

        outfielder.addActionListener(e -> 
        {
            new AddPlayerDialog(this.currentTeam,"Outfielders", this);
            System.out.println(this.currentTeam);
        });
    }

    @Override
    public void subscribe(ISubscriber subscriber) 
    {
        subs.add(subscriber);        
    }

    @Override
    public void unsubscribe(ISubscriber subscriber)
    {
        subs.remove(subscriber);        
    }

    @Override
    public void sendNotification(List<ISubscriber> subscribers, Object change, int code) 
    {
        for (ISubscriber iSubscriber : subscribers) 
        {
             iSubscriber.recieveUpdate(change, code);
        }    
    }

    @Override
    public void sendNotification(List<ISubscriber> subscribers, Object change) 
    {
        for (ISubscriber iSubscriber : subscribers) 
        {
            iSubscriber.recieveUpdate(change);
        }
    }

    @Override
    public void recieveUpdate(Object change) 
    {
        return;        
    }

    /**
     * @apiNote CODE LIST:
     * 0 -> new team
     * 1 -> new player
     * 123 -> team change
     * 1452316 -> new pitcher made
     * 145233 -> new catcher made
     * 1452310 -> new infielder made
     * 1452315 -> new outfielder made
     * @param change change to observe
     * @param code code to determine the change
     */
    @Override
    public void recieveUpdate(Object change, int code) 
    {
        switch(code)
        {
            case 0, 1452316, 145233, 1452310, 1452315 :
                sendNotification(subs, change, code);
            break;

            case 1 :

            break;

            case 123 :
                this.currentTeam = (String) change;
                System.out.println(currentTeam);
            break;
        }        
    }

    @Override
    public void subscribeTo(ISubscribable subscribable) 
    {
        subscribable.subscribe(this);        
    }
}
