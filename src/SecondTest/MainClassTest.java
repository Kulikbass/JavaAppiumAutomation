package SecondTest;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void SecondTest(){
        MainClass secondTest = new MainClass();
        Assert.assertTrue("The number returned by the getClassNumber method is less than 45",secondTest.getClassNumber()>45);
    }
}
