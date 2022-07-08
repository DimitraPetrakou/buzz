package Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CorrectAnswerTest {
    public CorrectAnswerTest(){
    }
    @Test
    public void testName(){
        CorrectAnswerMode cm = new CorrectAnswerMode();
        String result = cm.getModeName();
        assertEquals("Correct Answer Mode",result);
    }
    @Test
    public void testPoints(){
        CorrectAnswerMode ca = new CorrectAnswerMode();
        int result = ca.getPoints(true,0);
        assertEquals(1000,result,0.0);
    }
    @Test
    public void testPoints1(){
        CorrectAnswerMode ca = new CorrectAnswerMode();
        int result = ca.getPoints1(false,0,true,true);
        assertEquals(0,result,0.0);
    }
    @Test
    public void testPoints2(){
        CorrectAnswerMode ca = new CorrectAnswerMode();
        int result = ca.getPoints2(true,0,true,true);
        assertEquals(1000,result,0.0);
    }
}
