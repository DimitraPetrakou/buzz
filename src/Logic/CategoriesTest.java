package Logic;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertEquals;

public class CategoriesTest {


    public CategoriesTest(){
    }
    @Test
    public void testQuestion(){
        Categories c = new Categories();
        File file = new File("cat1.txt");
        c.Init(file);
        String result = c.getQuestion(1);
        String expResult = "What river runs through Baghdad ?";
        assertEquals(expResult,result);
    }
    @Test
    public void testAnswer(){
        Categories c = new Categories();
        File file = new File("cat4.txt");
        c.Init(file);
        String result = c.getAnswer(5);
        String expResult ="3";
        assertEquals(expResult,result);
    }
    @Test
    public void testOptions(){
        Categories c = new Categories();
        File file = new File("cat5.txt");
        c.Init(file);
        String result = c.getOption(2,10);
        String expResult = "Snickers";
        assertEquals(expResult,result);
    }


}
