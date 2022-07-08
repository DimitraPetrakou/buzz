package Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ThermometerTest {
    public ThermometerTest(){
    }
    @Test
    public void testName(){
        Thermometer t = new Thermometer();
        String result = t.getModeName();
        assertEquals("Thermometer Mode",result);
    }
    @Test
    public void testPoints(){
        Thermometer t = new Thermometer();
        int result = t.getPoints(true,0);
        assertEquals(0,result,0.0);
    }
    @Test
    public void testPoints1(){
        Thermometer t = new Thermometer();
        t.getPoints2(true,0,true,false);
        int result = t.getPoints1(true,0,true,true);
        assertEquals(5000,result,0.0);
    }
    @Test
    public void testPoints2(){
        Thermometer t =new Thermometer();
        t.getPoints1(true,0,true,true);
        int result = t.getPoints2(true,0,true,false);
        assertEquals(0,result,0.0);
    }

}
