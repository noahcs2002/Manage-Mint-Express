package Graphics;

import javax.swing.*;

import Interfaces.ISubscribable;
import Interfaces.ISubscriber;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class UpperNavbar extends JMenuBar implements ISubscribable, ISubscriber
{
    ArrayList<ISubscriber> subs;

    
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

    public UpperNavbar()
    {
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
            AddTeamDialog d = new AddTeamDialog(this);

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
     * @param change change to observe
     * @param code code to determine the change
     */
    @Override
    public void recieveUpdate(Object change, int code) 
    {
        switch(code)
        {
            case 0 :
                sendNotification(subs, change, 0);
            break;

            case 1 :

            break;
        }        
    }

    @Override
    public void subscribeTo(ISubscribable subscribable) 
    {
        // TODO Auto-generated method stub
        
    }


}
