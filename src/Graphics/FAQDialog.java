package Graphics;

import javax.swing.JDialog;
import javax.swing.JList;

public class FAQDialog extends JDialog
{
    public FAQDialog()
    {
        this.setTitle("FAQ's");

        final String[] faqs = 
        {
            "Q. Who developed this? A. Noah Sternberg, a student at Murray State University.",
            "Q. What was this developed for? A. A final project for an Advance Objects class.",
            "Q. How long did this take? A. Too long.",
            "Q. Will this ever get more features? A. I don't know.",
            "Q. Why did you make ___ design choice? A. Because I'm bad at graphic design.",
        };

        JList<String> list = new JList<>(faqs);

        this.add(list);
        this.setLocationRelativeTo(null);
        this.setSize(500,150);
        this.setVisible(true);
    }
}
