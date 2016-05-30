import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SeleniumTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
        String url = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub",
                sauceUsername, sauceAccessKey);
        String tunnelID = System.getenv("TRAVIS_JOB_NUMBER");

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "50.0");
        caps.setCapability("tunnel-identifier", tunnelID);

        driver = new RemoteWebDriver(new URL(url), caps);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSauceWikiLogoExists() {
        driver.get("https://wiki.saucelabs.com/");
        WebElement logo = driver.findElement(By.id("logo"));
        Assert.assertNotNull(logo);
    }
}
