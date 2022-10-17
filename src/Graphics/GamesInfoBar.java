package Graphics;

import Controllers.SqlController;
import javax.swing.*;
import java.awt.*;

public class GamesInfoBar extends JPanel
{
    SqlController controller;

    public GamesInfoBar()
    {
        controller = new SqlController();

        this.setLayout(new FlowLayout());

        JLabel recordLabel = new JLabel(this.getRecord());

        this.add(recordLabel);
    }

    private String getRecord()
    {
        String result = "";

        double[] record = controller.getRecord();

        result += "([W]" + record[0] + " : " + record[1] + "[L]) ";

        double rec = record[0]/(record[0] + record[1]) * 100;

        result += rec + "%";

        return result;
    }


}
