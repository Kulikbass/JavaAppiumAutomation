package FirstTest;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void firstTest(){
        MainClass testMethod = new MainClass();
        Assert.assertTrue("getLocalNumber method isn't equal 14",testMethod.getLocalNumber()==14);
    }


}
