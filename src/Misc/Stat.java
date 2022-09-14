package Misc;
public class Stat 
{
    private String statDescription;
    private double stat;
    
    public Stat(String statDescription, double stat)
    {
        this.statDescription = statDescription;
        this.stat = stat;
    }

    public Stat(Stat s)
    {
        this.statDescription = s.statDescription;
        this.stat = s.stat;
    }
    
    @Override
    public String toString()
    {
        return statDescription + ": " + stat;
    }

    //#region Getters and Setters
    public String getStatDescription() 
    {
        return this.statDescription;
    }
    
    public void setStatDescription(String statDescription) 
    {
        this.statDescription = statDescription;
    }
    
    public double getStat() 
    {
        return this.stat;
    }
    
    public void setStat(double stat) 
    {
        this.stat = stat;
    }
    //#endregion
}
