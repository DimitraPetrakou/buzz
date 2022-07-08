package Logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QuickAnswerTest {
    public QuickAnswerTest(){
    }
    @Test
    public void testName(){
        QuickAnswer qa = new QuickAnswer();
        String result = qa.getModeName();
        assertEquals("Quick Answer Mode",result);
    }
    @Test
    public void testPoints(){
        QuickAnswer qa = new QuickAnswer();
        int result = qa.getPoints(true,0);
        assertEquals(0,result,0.0);
    }
    @Test
    public void testPoints1(){
        QuickAnswer qa = new QuickAnswer();
        int result = qa.getPoints1(true,0,true,true);
        assertEquals(1000,result,0.0);
    }
    @Test
    public void testPoints2(){
        QuickAnswer qa = new QuickAnswer();
        qa.getPoints1(false,0,true,true);
        int result = qa.getPoints2(true,0,false,true);
        assertEquals(1000,result,0.0);
    }
    @Test
    public void testPoints2_2(){
        QuickAnswer qa = new QuickAnswer();
        qa.getPoints1(true,0,true,true);
        int result = qa.getPoints2(true,0,false,true);
        assertEquals(500,result,0.0);
    }
}
