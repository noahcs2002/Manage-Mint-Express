package test;

import org.junit.Assert;
import org.junit.Test;

import Members.Pitcher;

public class PitcherTest 
{

    @Test
    public void Make()
    {
        Pitcher pitcher = new Pitcher();
        Assert.assertEquals(0, pitcher.getEarnedRunAverage(), 0);
        Assert.assertEquals(0, pitcher.getEarnedRuns(), 0);
        Assert.assertEquals(0, pitcher.getHits(), 0);
        Assert.assertEquals(0, pitcher.getHomeruns(), 0);
        Assert.assertEquals(0, pitcher.getInningsPitched(), 0);
        Assert.assertEquals(0, pitcher.getRuns(), 0);
        Assert.assertEquals(0, pitcher.getSaves(), 0);
        Assert.assertEquals(0, pitcher.getStrikeOuts(), 0);
        Assert.assertEquals(0, pitcher.getWHIP(), 0);
        Assert.assertEquals(0, pitcher.getWalks(), 0);

        Pitcher pitcher2 = new Pitcher(1,2,3,4,5,6,7,8,9,10);
        Assert.assertEquals(9, pitcher2.getEarnedRunAverage(), 0);
        Assert.assertEquals(4, pitcher2.getEarnedRuns(), 0);
        Assert.assertEquals(2, pitcher2.getHits(), 0);
        Assert.assertEquals(7, pitcher2.getHomeruns(), 0);
        Assert.assertEquals(1, pitcher2.getInningsPitched(), 0);
        Assert.assertEquals(3, pitcher2.getRuns(), 0);
        Assert.assertEquals(8, pitcher2.getSaves(), 0);
        Assert.assertEquals(6, pitcher2.getStrikeOuts(), 0);
        Assert.assertEquals(10, pitcher2.getWHIP(), 0);
        Assert.assertEquals(5, pitcher2.getWalks(), 0);

    }

    
}
