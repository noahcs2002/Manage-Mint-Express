package Misc;
public class Stat 
{
    private String statDescription;
    private double stat;
    
    /**
     * {@summary} Makes a Stat Object
     * @param statDescription An english description of a Stat
     * @param stat The actual statistic
     */
    public Stat(String statDescription, double stat)
    {
        this.statDescription = statDescription;
        this.stat = stat;
    }

    /**
     * {@summary} Convience Method to reset a statistic
     * @param s
     */
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
