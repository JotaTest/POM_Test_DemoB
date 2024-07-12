package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;


public class CartPage extends BasePage {
    private By addItemToCarBy = By.className("btn-lg");
    private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]"
    );
    private By nameFieldBy = By.id("name");
    private By countryFieldBy = By.id("country");
    private By cityFieldBy = By.id("city");
    private By crediCardBy = By.id("card");
    private By monthFieldBy = By.id("month");
    private By yearFieldBy = By.id("year");
    private By purchaseButtonBy = By.xpath("//button[contains(text(), 'Purchase')]");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void addItemToCar() {
        WebElement addButton = wait.until(d -> d.findElement(addItemToCarBy));
        addButton.click();
    }

    public String getMessage() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert.getText();
        } catch (Exception ex) {
            Reporter.log("No message was found: " + ex.getMessage());
            return null;
        }
    }

    public boolean successfulPurchase() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean containsItem(String itemName) {
        String locator = String.format("//td[contains(text(), '%s')]", itemName);
        try {
            wait.until(d -> d.findElement(By.xpath(locator)).isDisplayed());
            return true;
        } catch (Exception ex) {
            Reporter.log("Item not found in a car " + ex.getMessage());
            return false;
        }

    }

    public void placeOrderFields(String name, String country, String city, String card, String month, String year) {
        // Click on Place Order button
        WebElement placeOrderButtonElement = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButtonElement.click();

        // Fill in the form fields
        wait.until(ExpectedConditions.elementToBeClickable(nameFieldBy)).sendKeys(name);
        driver.findElement(countryFieldBy).sendKeys(country);
        driver.findElement(cityFieldBy).sendKeys(city);
        driver.findElement(crediCardBy).sendKeys(card);
        driver.findElement(monthFieldBy).sendKeys(month);
        driver.findElement(yearFieldBy).sendKeys(year);

        // Click on Purchase button
        driver.findElement(purchaseButtonBy).click();
    }
}
