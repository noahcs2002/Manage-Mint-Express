package Graphics;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Engine.SQL.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;


public class InformationPanel extends JPanel implements ISubscribable, ISubscriber
{
    JTable informationTable;
    SqlController connectionDriver = new SqlController();

    private String currentPositionSelection;
    private String currentTeamSelection;

    ArrayList<ISubscriber> subs = new ArrayList<>();

    private final String[] pitchingTableColumnNames = 
    {
        "Player",
        "Team",
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
        "Player",
        "Team",
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

    private final String[] infielderTableColumnNames = 
    {
        "Player",
        "Team",
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
        "Team",
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


    public InformationPanel(String teamName)
    {
        currentPositionSelection = "Pitchers";
        currentTeamSelection = teamName;
        try
        {
            DefaultTableModel tableModel = new DefaultTableModel(pitchingTableColumnNames, 0);
            
            ArrayList<Object[]> results = connectionDriver.getPitchers(teamName);

            tableModel.addRow(pitchingTableColumnNames);

            for (Object[] objects : results) 
            {
                tableModel.addRow(objects);    
            }
                
            this.informationTable = new JTable(tableModel);
            this.add(informationTable);

        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG> EXCEPTION HANDLED (PitchersPanel): " + ex.getMessage());
        }
    }

   

    public void updateInformation(String team, String position)
    {
        DefaultTableModel tableModel ;
        ArrayList<Object[]> results ;

        switch(position)
        {
            case "Pitchers" :
                results = connectionDriver.getPitchers(team);
                tableModel = new DefaultTableModel(pitchingTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(pitchingTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 

                this.repaint();
                this.revalidate();
            break;

            case "Catchers" :
                results = connectionDriver.getCatchers(team);
                tableModel = new DefaultTableModel(catchingTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(catchingTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 
                
                this.repaint();
                this.revalidate();
            break;

            case "Infielders" :
                results = connectionDriver.getInfielders(team);
                tableModel = new DefaultTableModel(infielderTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(infielderTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 

                this.repaint();
                this.revalidate();
            break;

            case "Outfielders" :
                results = connectionDriver.getOutfielders(team);
                tableModel = new DefaultTableModel(outfielderTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(outfielderTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 

                this.repaint();
                this.revalidate();
            break;
        }
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        switch(infoCode)
        {
            case TEAM_CHANGE : 
                updateInformation((String)change, currentPositionSelection);
            break;

            case POSITION_CHANGE :
                updateInformation(currentTeamSelection, (String) change);
            break;

            case NEW_PITCHER, NEW_CATCHER, NEW_INFIELDER, NEW_OUTFIELDER, CLEARED_DATA :
                updateInformation(currentTeamSelection, currentPositionSelection);
            break;

            default : break;
        }
    }

    @Override
    public void subscribe(ISubscribable subscribable) 
    {
        subscribable.addSubsriber(this);
    }

    @Override
    public void unsubscribe(ISubscribable subscribable) 
    {
        subscribable.removeSubscriber(this);
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
