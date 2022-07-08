package Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BetModeTest {

    public BetModeTest() {
    }
    @Test
    public void testName(){
        BetMode bm = new BetMode();
        String result = bm.getModeName();
        assertEquals("Bet Mode",result);

    }
    @Test
    public void testPoints(){
        BetMode bm = new BetMode();
        bm.setBet(500);
        int result = bm.getPoints(true,0);
        assertEquals(500,result,0.0);
    }
    @Test
    public void testPoints1(){
        BetMode bm = new BetMode();
        bm.setBet1(250);
        int result = bm.getPoints1(false,0,false,false);
        assertEquals(-250,result,0.0);
    }
    @Test
    public void testPoints2(){
        BetMode bm = new BetMode();
        bm.setBet2(750);
        int result = bm.getPoints2(false,0,false,false);
        assertEquals(-750,result,0.0);
    }
}