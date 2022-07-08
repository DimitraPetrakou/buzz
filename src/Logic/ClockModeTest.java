package Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClockModeTest {
    public ClockModeTest(){
    }
    @Test
    public void testName(){
        ClockMode cm = new ClockMode();
        String result = cm.getModeName();
        assertEquals("Clock Mode",result);
    }
    @Test
    public void testPoints(){
        ClockMode cm = new ClockMode();
        int result = cm.getPoints(true,2.4);
        assertEquals(480,result,0.0);
    }
    @Test
    public void testPoints1(){
        ClockMode cm = new ClockMode();
        int result = cm.getPoints1(false,3.2,true,true);
        assertEquals(0,result,0.0);
    }
    @Test
    public void testPoints2(){
        ClockMode cm = new ClockMode();
        int result = cm.getPoints2(true,3.2,true,true);
        assertEquals(640,result,0.0);
    }
}
