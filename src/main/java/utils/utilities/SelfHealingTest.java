package utils.utilities;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import utils.driverConfig.DriverFactory;

import static utils.driverConfig.DriverHolder.setDriver;

@Listeners(TestExecutionListener.class)
public class SelfHealingTest {

    public static WebDriver driver;

    public static SelfHealingDriver withChromeOptions(String url) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--allow-running-insecure-content --disable-popup-blocking --disable-dev-shm-usage");
        options.addArguments("--disable-infobars --test-type --disable-extensions --disable-translate");
        options.addArguments("--ignore-certificate-errors --incognito --no-sandbox --disable-download-notification");
        options.addArguments("use-fake-ui-for-media-stream");

        WebDriver delegate = DriverFactory.getNewInstance("chrome");
        setDriver(delegate);
        driver = SelfHealingDriver.create(delegate);
        driver.manage().window().maximize();
        driver.navigate().to(url);

        return SelfHealingDriver.create(delegate);
    }

    public static SelfHealingDriver withEdge(String url) {
        WebDriver delegate = DriverFactory.getNewInstance("edge");
        setDriver(delegate);
        driver = SelfHealingDriver.create(delegate);
        driver.manage().window().maximize();
        driver.navigate().to(url);
        
        return SelfHealingDriver.create(delegate);
    }

}
