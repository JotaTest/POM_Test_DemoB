package driversManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;


public class DriversController {
    protected WebDriver driver;

    public void setupDriver(String browser) {
        if (Objects.equals(browser, "edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else {
            WebDriverManager.chromedriver().setup();;
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
    }

    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
