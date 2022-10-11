package Graphics;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
import java.net.URI;
import java.awt.*;

public class HelpBar extends JMenuBar
{
    
    JMenu help = new JMenu("?");
    JMenuItem faq = new JMenuItem("FAQ's");
    JMenuItem howToUse = new JMenuItem("How to use");
    JMenuItem documentation = new JMenuItem("Docs");
    private String uri = "https://github.com/noahcs2002/Mangage-Mint-Express";

    public HelpBar()
    {
        help.add(faq);
        help.add(howToUse);
        help.add(documentation);

        this.add(help);

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
}
