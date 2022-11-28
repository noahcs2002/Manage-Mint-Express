package Graphics;

import javax.swing.JDialog;
import javax.swing.JList;

/**
 * FAQs
 */
public class FAQDialog extends JDialog
{
    public FAQDialog()
    {
        this.setTitle("FAQ's");

        final String[] faqs = 
        {
            "1. To add a player, click 'Add Player' in the navbar, then select your type. " +
            "Then, fill in ALL THE FIELDS, and hit tab one time after entering the last value, then hit confirm.",
            "2. Hitting 'Clear All' will clear all entries OF THAT SPECIFIC POSITION. Clear All while on pitchers clears pitchers etc.",
            "3. If you have issues adding a player, make sure all values are full, and then go to the last column and hit tab, then the confirm button.",
            "4. Inj. and Sus. MUST be entered as true or false."
        };

        JList<String> list = new JList<>(faqs);

        this.add(list);
        this.setLocation(200,300);
        this.setSize(1400,150);
        this.setVisible(true);
    }
}
