package Graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import Controllers.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

/**
 * Dialog for recording of a game.
 */
public class RecordGameDialog extends JDialog implements ISubscribable
{

    // Display the table, record the information,
    // add to pastGamesTable, remove from futureGamesTable
    // by filtering for versus and time
    ArrayList<ISubscriber> subs = new ArrayList<>();
    JPanel panel;
    DefaultTableModel tableModel;
    JTable table;
    final String[] columnHeaders = 
    {
        "Date",
        "Versus?",
    };

    private final SqlController connection = new SqlController();
    

    public RecordGameDialog()
    {
        panel = new JPanel();

        tableModel = new DefaultTableModel(columnHeaders, 0);
        tableModel.addRow(columnHeaders);

        tableModel.addRow(new Object[]{});

        table = new JTable(tableModel);
        panel.setLayout(new BorderLayout());
        panel.add(table, BorderLayout.CENTER);

        JPanel northPanel = new JPanel();
        JButton saveButton = new JButton("Save Changes");


        saveButton.addActionListener(e -> 
        {
            String versus = (String) table.getModel().getValueAt(1, 1);
            String date = (String) table.getModel().getValueAt(1, 0);

            connection.recordGame(versus, date);
            this.alert(null, null);
            this.dispose();
        });



        northPanel.add(saveButton);

        panel.add(northPanel, BorderLayout.NORTH);

        this.add(panel);

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Record Changes");
        this.setLocationRelativeTo(null);
    }


    @Override
    public void alert(Object change, InfoCode infoCode)
    {
        for(ISubscriber sub : subs)
        {
            sub.getAlert(change, infoCode);
        }
    }

    @Override
    public void addSubsriber(ISubscriber subscriber)
    {
        this.subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber)
    {
        this.subs.remove(subscriber);
    }

    



}
