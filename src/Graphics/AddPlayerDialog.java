package Graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DTOs.Catcher;
import DTOs.Infielder;
import DTOs.Outfielder;
import DTOs.Pitcher;
import Engine.SQL.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

public class AddPlayerDialog extends JDialog implements ISubscribable
{
    private String position;
    private String teamChoice;
    JTable emptyTable = new JTable();
    ArrayList<ISubscriber> subs;

    private Pitcher pitcher;
    private Catcher catcher;
    private Infielder infielder;
    private Outfielder outfielder;

    private final String[] pitchingTableColumnNames = 
    {
        "Player",
        "IP",
        "H",
        "R",
        "ER",
        "W",
        "SO",
        "HR",
        "Saves",
        "ERA",
        "WHIP",
        "Inj.",
        "Sus.",
        "Injury:",
        "Suspended Reason: ",
        "Number"
    };

    private final String[] catchingTableColumnNames = 
    {
        "Name",
        "GP",
        "GS",
        "IP",
        "TC",
        "PO",
        "A",
        "E",
        "DP",
        "Inj",
        "Injury",
        "Sus.",
        "Suspension Reason",
        "Number"
    };

    private final String[] infielderTableColumnNames = 
    {
        "Player",
        "GP",
        "GS",
        "IP",
        "TC",
        "PO",
        "A",
        "E",
        "DP",
        "Inj",
        "Injury",
        "Sus.",
        "Suspension Reason",
        "Pos.",
        "Number"
    };

    private final String[] outfielderTableColumnNames = 
    {
        "Player",
        "GP",
        "GS",
        "IP",
        "TC",
        "PO",
        "A",
        "E",
        "DP",
        "Inj.",
        "Injury",
        "Sus.",
        "Suspension Reason",
        "Pos.",
        "Number"
    };

    public AddPlayerDialog(String team, String position)
    {
        subs = new ArrayList<>();
        this.teamChoice = team;
        this.position = position;
        SqlController connectionDriver = new SqlController();
        DefaultTableModel model = new DefaultTableModel();
        JButton confButton = new JButton("Confirm");

        try
        {
            switch(position)
            {
                case "Pitchers" :
                    position = "Pitcher";
                    model = new DefaultTableModel(pitchingTableColumnNames, 0);
                    model.addRow(pitchingTableColumnNames);
                    model.addRow(new Object[]{});
                break;

                case "Catchers" :
                    position = "Catcher";
                    model = new DefaultTableModel(catchingTableColumnNames, 0);
                    model.addRow(catchingTableColumnNames);
                    model.addRow(new Object[]{});
                break;

                case "Infielders" :
                    position = "Infielder";
                    model = new DefaultTableModel(infielderTableColumnNames, 0);
                    model.addRow(infielderTableColumnNames);
                    model.addRow(new Object[]{});
                break;

                case "Outfielders" :
                    position = "Outfielder";
                    model = new DefaultTableModel(outfielderTableColumnNames, 0);
                    model.addRow(outfielderTableColumnNames);
                    model.addRow(new Object[]{});
                break;
            }

            emptyTable = new JTable(model);
        }
        catch(Exception ex)
        {
            System.out.println("Exception Thrown in AddPlayerDialog");
        }

        confButton.addActionListener(e -> 
        {
            if(this.position.equals("Pitchers"))
            {

                var name = emptyTable.getModel().getValueAt(1, 0).toString();
                var inningsPitched = Double.parseDouble(emptyTable.getModel().getValueAt(1, 1).toString());
                var hits = Double.parseDouble(emptyTable.getModel().getValueAt(1, 2).toString());
                var runs = Double.parseDouble(emptyTable.getModel().getValueAt(1, 3).toString());
                var earnedRuns = Double.parseDouble(emptyTable.getModel().getValueAt(1, 4).toString());
                var walks = Double.parseDouble(emptyTable.getModel().getValueAt(1, 5).toString());
                var strikeouts = Double.parseDouble(emptyTable.getModel().getValueAt(1, 6).toString());
                var homeruns = Double.parseDouble(emptyTable.getModel().getValueAt(1, 7).toString());
                var saves = Double.parseDouble(emptyTable.getModel().getValueAt(1, 8).toString());
                var era = Double.parseDouble(emptyTable.getModel().getValueAt(1, 9).toString());
                var whip = Double.parseDouble(emptyTable.getModel().getValueAt(1, 10).toString());
                var isInjured = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 11).toString());
                var isSuspended = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 12).toString());
                var injury = emptyTable.getModel().getValueAt(1, 13).toString();
                var suspension = emptyTable.getModel().getValueAt(1, 14).toString();
                var number = emptyTable.getModel().getValueAt(1, 15).toString();

                int isInjuredBit = 0;
                int isSuspendedBit = 0;

                if(isInjured == true)
                    isInjuredBit = 1;
                if(isSuspended == true)
                    isSuspendedBit = 1;    

                pitcher = new Pitcher(
                    name, this.teamChoice, inningsPitched, hits, runs, earnedRuns, walks, strikeouts,
                    homeruns, saves, era, whip, isInjuredBit, isSuspendedBit, injury, suspension, number, teamChoice
                );

                connectionDriver.makePitcher(pitcher);
                this.alert(pitcher, InfoCode.NEW_PITCHER);

                this.dispose();
            }

            if(this.position.equals("Catchers"))
            {
                var name = emptyTable.getModel().getValueAt(1, 0).toString();
                var gamesPlayed = Double.parseDouble(emptyTable.getModel().getValueAt(1, 1).toString());
                var gamesStarted = Double.parseDouble(emptyTable.getModel().getValueAt(1, 2).toString());
                var inningsPlayed = Double.parseDouble(emptyTable.getModel().getValueAt(1, 3).toString());
                var totalChances = Double.parseDouble(emptyTable.getModel().getValueAt(1, 4).toString());
                var putOuts = Double.parseDouble(emptyTable.getModel().getValueAt(1, 5).toString());
                var assists = Double.parseDouble(emptyTable.getModel().getValueAt(1, 6).toString());
                var errors = Double.parseDouble(emptyTable.getModel().getValueAt(1, 7).toString());
                var doublePlaysTurned = Double.parseDouble(emptyTable.getModel().getValueAt(1, 8).toString());
                var isInjured = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 9).toString());
                var injury = emptyTable.getModel().getValueAt(1, 10).toString();
                var isSuspended = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 11).toString());
                var suspension = emptyTable.getModel().getValueAt(1, 12).toString();
                var number = emptyTable.getModel().getValueAt(1, 13).toString();

                int isInjuredBit = 0;
                int isSuspendedBit = 0;

                if(isInjured == true)
                    isInjuredBit = 1;
                if(isSuspended == true)
                    isSuspendedBit = 1;    

                catcher = new Catcher(
                    name, this.teamChoice, gamesPlayed, gamesStarted,
                    inningsPlayed, totalChances, putOuts, assists, errors,
                    doublePlaysTurned, isInjuredBit, injury, isSuspendedBit, suspension,
                    number
                );

                connectionDriver.makeCatcher(catcher);
                this.alert(catcher, InfoCode.NEW_CATCHER);

                this.dispose();
            }
            
            if(this.position.equals("Infielders"))
            {
                var name = emptyTable.getModel().getValueAt(1, 0).toString();
                var gamesPlayed = Double.parseDouble(emptyTable.getModel().getValueAt(1, 1).toString());
                var gamesStarted = Double.parseDouble(emptyTable.getModel().getValueAt(1, 2).toString());
                var inningsPlayed = Double.parseDouble(emptyTable.getModel().getValueAt(1, 3).toString());
                var totalChances = Double.parseDouble(emptyTable.getModel().getValueAt(1, 4).toString());
                var putOuts = Double.parseDouble(emptyTable.getModel().getValueAt(1, 5).toString());
                var assists = Double.parseDouble(emptyTable.getModel().getValueAt(1, 6).toString());
                var errors = Double.parseDouble(emptyTable.getModel().getValueAt(1, 7).toString());
                var doublePlaysTurned = Double.parseDouble(emptyTable.getModel().getValueAt(1, 8).toString());
                var isInjured = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 9).toString());
                var injury = emptyTable.getModel().getValueAt(1, 10).toString();
                var isSuspended = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 11).toString());
                var suspension = emptyTable.getModel().getValueAt(1, 12).toString();
                var pos = emptyTable.getModel().getValueAt(1, 13).toString();
                var number = emptyTable.getModel().getValueAt(1, 14).toString();

                int isInjuredBit = 0;
                int isSuspendedBit = 0;

                if(isInjured == true)
                    isInjuredBit = 1;
                if(isSuspended == true)
                    isSuspendedBit = 1;    

                infielder = new Infielder(
                    name, this.teamChoice, gamesPlayed, gamesStarted,
                    inningsPlayed, totalChances, putOuts, assists, errors,
                    doublePlaysTurned, isInjuredBit, injury, isSuspendedBit,
                    suspension, pos, number
                );

                connectionDriver.makeInfielder(infielder);
                this.alert(infielder, InfoCode.NEW_INFIELDER);

                this.dispose();
            }
            if(this.position.equals("Outfielders"))
            {
                var name = emptyTable.getModel().getValueAt(1, 0).toString();
                var gamesPlayed = Double.parseDouble(emptyTable.getModel().getValueAt(1, 1).toString());
                var gamesStarted = Double.parseDouble(emptyTable.getModel().getValueAt(1, 2).toString());
                var inningsPlayed = Double.parseDouble(emptyTable.getModel().getValueAt(1, 3).toString());
                var totalChances = Double.parseDouble(emptyTable.getModel().getValueAt(1, 4).toString());
                var putOuts = Double.parseDouble(emptyTable.getModel().getValueAt(1, 5).toString());
                var assists = Double.parseDouble(emptyTable.getModel().getValueAt(1, 6).toString());
                var errors = Double.parseDouble(emptyTable.getModel().getValueAt(1, 7).toString());
                var doublePlaysTurned = Double.parseDouble(emptyTable.getModel().getValueAt(1, 8).toString());
                var isInjured = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 9).toString());
                var injury = emptyTable.getModel().getValueAt(1, 10).toString();
                var isSuspended = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 11).toString());
                var suspension = emptyTable.getModel().getValueAt(1, 12).toString();
                var pos = emptyTable.getModel().getValueAt(1, 13).toString();
                var number = emptyTable.getModel().getValueAt(1, 14).toString();

                int isInjuredBit = 0;
                int isSuspendedBit = 0;

                if(isInjured == true)
                    isInjuredBit = 1;
                if(isSuspended == true)
                    isSuspendedBit = 1;    

                outfielder = new Outfielder(
                    name, this.teamChoice, gamesPlayed, gamesStarted,
                    inningsPlayed, totalChances, putOuts, assists, errors,
                    doublePlaysTurned, isInjuredBit, injury, isSuspendedBit,
                    suspension, pos, number
                );

                connectionDriver.makeOutfielder(outfielder);
                this.alert(outfielder, InfoCode.NEW_OUTFIELDER);

                this.dispose();
            }
        });
        
        this.setJMenuBar(new Navbar(position));
        this.setLayout(new BorderLayout());
        this.setTitle("Player Creation Wizard");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1200, 200);
        this.setLocationRelativeTo(null);
        this.add(emptyTable, BorderLayout.CENTER);

        JPanel helperPanel = new JPanel();
        helperPanel.add(confButton);

        this.add(helperPanel, BorderLayout.SOUTH);
    }

    public Pitcher getPitcher()
    {
        return this.pitcher;
    }

    public Catcher getCatcher()
    {
        return this.catcher;
    }

    public Infielder getInfielder()
    {
        return this.infielder;
    }

    public Outfielder getOutfielder()
    {
        return this.outfielder;
    }

    @Override
    public void alert(Object change, InfoCode infoCode) 
    {
        for (ISubscriber sub : subs) 
            sub.getAlert(change, infoCode);
    }

    @Override
    public void addSubsriber(ISubscriber subscriber) 
    {
        Objects.requireNonNull(subscriber);

        if(!subs.contains(subscriber))
            subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) 
    {
        Objects.requireNonNull(subscriber);

        if(subs.contains(subscriber))
            subs.remove(subscriber);    
    }
}
