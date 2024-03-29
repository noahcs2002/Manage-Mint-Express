package Graphics;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controllers.SqlController;
import Engine.ErrorHandler.ErrorHandler;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

/**
 * Main information panel.
 * Subscriber.
 * Subscribable.
 */
public class InformationPanel extends JPanel implements ISubscribable, ISubscriber
{
    JTable informationTable;
    SqlController connectionDriver = new SqlController();

    private String currentPositionSelection;
    private String currentTeamSelection;

    ArrayList<ISubscriber> subs = new ArrayList<>();

    DefaultTableModel tableModel;

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
            tableModel = new DefaultTableModel(pitchingTableColumnNames, 0);
            
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
            ErrorHandler.handle("<DEBUG> EXCEPTION HANDLED (PitchersPanel): " + ex.getMessage());
        }
    }

    /**
     * Update current information
     * @param team Team to update with.
     * @param position Position to update with.
     */
    private void updateInformation(String team, String position)
    {
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

            case UPDATE_DATA:
                this.updateInformation(change.toString());
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
        for(ISubscriber sub : subs)
            sub.getAlert(change, infoCode);
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

    public void updateInformation(String pos)
    {
        ArrayList<Object[]> data = new ArrayList<>();

        for(int i = 0; i < tableModel.getRowCount(); i += 1)
        {
            Object[] objs = new Object[tableModel.getColumnCount()];

            for(int j = 0; j < tableModel.getColumnCount(); j += 1)
            {
                objs[j] = tableModel.getValueAt(i, j);
            }

            data.add(objs);
        }

        connectionDriver.applyUpdate(data, this.currentTeamSelection, pos);
    }
    
}