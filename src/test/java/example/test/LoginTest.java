package example.test;

import example.pages.HomePage;
import example.pages.LoginPage;
import example.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Scanner;

public class
LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Parameters({ "email", "password" })
    @Test
    public void testValidLogin(final String email, final String password) {
        Assert.assertEquals(loginPage.getPageTitle(), "PlaceLab");
        Assert.assertTrue(loginPage.isHeaderDisplayed());
        Assert.assertTrue(loginPage.isLoginFormDisplayed());
        Assert.assertTrue(loginPage.isEmailFieldDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submitLoginForm();

        final String expectedRole = "Group Admin";
        final String actualRole = homePage.getUserRole();
        Assert.assertEquals(actualRole, expectedRole, "Validate user role for logged in user.");

        homePage.clickUserRole();
        Assert.assertTrue(homePage.isSignOutLinkDisplayed());

        homePage.clickSignOutLink();
        Assert.assertTrue(loginPage.isLoginFormDisplayed());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void teardown() {
        driver.close();
    }

    @DataProvider(name = "credentials")
    public Object[][] provideCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        scanner.close();

        return new Object[][] {{ email, password }};
    }
}

