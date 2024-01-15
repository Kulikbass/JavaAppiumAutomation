package ThirdTest;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetClassString(){
        MainClass stringTest = new MainClass();
        Assert.assertTrue("The string returned by the getClassString method isn't contains word Hello or hello",
                stringTest.getClassString().contains("Hello") || stringTest.getClassString().contains("hello"));
    }
}
