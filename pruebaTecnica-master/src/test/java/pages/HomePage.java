package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    private By navbarBy = By.id("nava");
    private By loginLinkBy = By.id("login2");
    private By modalDialogBy = By.id("logInModalLabel");
    private By categoriesContainerBy = By.id("contcont");
    private By categoryBy = By.id("itemc");
    private By productTitleBy = By.className("card-title");

    public HomePage(WebDriver driver) {
        super(driver);
        this.url = "https://www.demoblaze.com/";
    }

    public void navigate() {
        driver.get(this.url);
        wait.until(d -> d.findElement(navbarBy).isDisplayed());
    }

    public LoginPage openLoginPage() {
        driver.findElement(loginLinkBy).click();
        wait.until(d -> d.findElement(modalDialogBy).isDisplayed());
        return new LoginPage(driver);
    }

    public CartPage selectItem(String itemName) {
        driver.findElement(By.linkText(itemName)).click();
        String locator = String.format("//*[contains(text(), '%s')]", itemName);
        wait.until(d -> d.findElement(By.xpath(locator)));

        return new CartPage(driver);
    }

    public void openCartDetails() {
        WebElement cartButton = wait.until(d -> d.findElement(By.id("cartur")));
        cartButton.click();
        wait.until(ExpectedConditions.urlContains("/cart.html"));
    }

    public List<String> getCategories() {
        List<WebElement> categories = driver.findElements(categoryBy);

        return categories.stream()
                .map(category -> category.getText())
                .collect(Collectors.toList());
    }

    public void selectCategory(String categoryName) {
        WebElement category = driver.findElement(categoriesContainerBy).findElement(By.linkText(categoryName));
        category.click();
        waitForAjax();
    }

    public List<String> getProductNames() {
        List<WebElement> products = driver.findElements(productTitleBy);

        return products.stream()
                .map(product -> product.getText())
                .collect(Collectors.toList());
    }
}
