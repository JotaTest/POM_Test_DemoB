package test;

import driversManager.DriversController;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends DriversController {
    private HomePage homePage;
    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod
    public void beforeTest(@Optional("chrome") String browser) {
        setupDriver(browser);
        homePage = new HomePage(driver);
        homePage.navigate();
        loginPage = homePage.openLoginPage();
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Given
        String username = "123";
        String password = "123";

        // When
        loginPage.login(username, password);

        // Then
        assertEquals(loginPage.getLoggedUser(), username);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"123", "321", "Wrong password."},     // Wrong password
                {"(/&#%", "84940", "User does not exist."},  // Nonexistent user
                {"", "", "Please fill out Username and Password."}  // Empty credentials
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedErrorMessage) {
        // When
        loginPage.login(username, password);

        // Then
        assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }

    @AfterMethod
    public void tearDown() {
        tearDownDriver();
    }
}