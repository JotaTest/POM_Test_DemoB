package test;

import driversManager.DriversController;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends DriversController {

    private HomePage homePage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        setupDriver(browser);
        homePage = new HomePage(driver);
        homePage.navigate();
        LoginPage loginPage = homePage.openLoginPage();
        loginPage.loginAndWait("123", "123");
    }

    @Test
    public void testAddItemToCart() {
        // Given
        String categoryName = "Phones";
        String itemName = "Nokia lumia 1520";

        // When
        homePage.selectCategory(categoryName);
        CartPage cartPage = homePage.selectItem(itemName);
        cartPage.addItemToCar();

        // Then
        assertEquals(cartPage.getMessage(), "Product added.");
    }

    @Test
    public void testAddItemToCartAndValidateCartDetails() {
        //Given
        String categoryName = "Phones";
        String itemName = "Nexus 6";

        //When
        homePage.selectCategory(categoryName);
        CartPage cartPage = homePage.selectItem(itemName);
        cartPage.addItemToCar();
        homePage.openCartDetails();

        //Then
        assertTrue(cartPage.containsItem(itemName));
    }

    @Test
    public void testPlaceOrder() {
        //Given
        String itemName = "Nexus 6";
        CartPage cartPage = homePage.selectItem(itemName);
        cartPage.addItemToCar();
        homePage.openCartDetails();

        //When
        String name = "Jorge";
        String country = "Colombia";
        String city = "Cartagena";
        String card = "2038947774";
        String month = "Julio";
        String year = "2024";
        cartPage.placeOrderFields(name, country, city, card, month, year);

        //Then
        assertTrue(cartPage.successfulPurchase(), "Purchase confirmation message is not visible");
    }

    @AfterMethod
    public void tearDown() {
        tearDownDriver();
    }
}
