import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void FirstTest() {

        //Каждый раз при запуске приложения из автотеста появляется дефолтный экран который приходится скипать таким образом чтобы перейти к поиску
        waitElementAndClick(By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find skip button",
                15);

        waitElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot fint Wiki search element",
                15);

        waitElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot Find Element search input ",
                15);

        waitForElementPresent(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15);


    }

    @Test
    public void testCancelSearch() {

        waitElementAndClick(By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find skip button",
                15);

        waitElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Wiki search element",
                15);

        waitElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot Find Element search input ",
                15);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find input element",
                15
        );

        waitElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cancel button error",
                15);

        waitForElementNotPresent(By.className("android.widget.ImageButton"),
                "Cancel button is present",
                15);

    }

    @Test
    public void testCompareArticleTitle() {
        waitElementAndClick(By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find skip button",
                15);

        waitElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot fint Wiki search element",
                15);



        waitElementAndClick(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
                "Cannot fint Java article element",
                15);

        WebElement titleElement = waitForElementPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Article title isn't present on page",
                15);

        String articleTitle = titleElement.getText();

        Assert.assertEquals(
                "We se unexpected title",
                "Java (programming language)",
                articleTitle);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds){
         WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
         element.clear();
         return element;
    }

}
