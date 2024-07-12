package test;

import driversManager.DriversController;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;

public class HomeTest extends DriversController {
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
    public void testCategoryNames() {
        // When
        List<String> categoryNames = homePage.getCategories();

        // Then
        assertEquals(categoryNames, Arrays.asList("Phones", "Laptops", "Monitors"));
    }

    @DataProvider(name = "categoryProvider")
    public Object[][] getCategoryData() {
        return new Object[][] {
                {"Phones", new String[] {
                        "Samsung galaxy s6",
                        "Iphone 6 32gb",
                        "HTC One M9"
                }},
                {"Laptops", new String[] {
                        "Sony vaio i5",
                        "Dell i7 8gb",
                        "MacBook Pro"
                }},
                {"Monitors", new String[] {
                        "Apple monitor 24",
                        "ASUS Full HD"
                }}
        };
    }

    @Test(dataProvider = "categoryProvider")
    public void testFilterByCategory(String category, String[] expectedProducts) {
        // When
        homePage.selectCategory(category);
        List<String> productNames = homePage.getProductNames();

        // Then
        assertThat(productNames, hasItems(expectedProducts));
    }

    @AfterMethod
    public void tearDown() {
        tearDownDriver();
    }
}
