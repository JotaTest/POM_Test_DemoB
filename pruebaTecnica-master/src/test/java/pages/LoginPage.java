package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.TestException;

public class LoginPage extends BasePage {
    private By usernameBy = By.id("loginusername");
    private By passwordBy = By.id("loginpassword");
    private By nameOfUserBy = By.id("nameofuser");
    private By loginButtonBy = By.xpath("//button[contains(text(), 'Log in')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String password) {
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(loginButtonBy).click();
    }

    public void loginAndWait(String userName, String password) {
        login(userName, password);
        if (!userName.equals(getLoggedUser())) {
            throw new TestException("Unable to log in with user: " + userName);
        }
    }

    public String getLoggedUser() {
        try {
            wait.until(d -> d.findElement(nameOfUserBy).isDisplayed());
            return driver.findElement(nameOfUserBy).getText().replace("Welcome ", "");
        } catch (Exception ex) {
            Reporter.log("No logged user message was found " + ex.getMessage());
            return null;
        }
    }

    public String getErrorMessage() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert.getText();
        } catch (Exception ex) {
            Reporter.log("No error message was found: " + ex.getMessage());
            return null;
        }
    }
}
