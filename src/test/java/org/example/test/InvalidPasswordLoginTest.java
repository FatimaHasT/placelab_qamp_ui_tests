package org.example.test;

import org.example.pages.LoginPage;
import org.example.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InvalidPasswordLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testInvalidPasswordLogin() {
        Assert.assertEquals(loginPage.getPageTitle(), "PlaceLab");
        Assert.assertTrue(loginPage.isHeaderDisplayed());
        Assert.assertTrue(loginPage.isLoginFormDisplayed());
        Assert.assertTrue(loginPage.isEmailFieldDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());

        loginPage.enterEmail("fatimahasanovic1994@gmail.com");
        loginPage.enterPassword("blabla");

        loginPage.submitLoginForm();

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials!");
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
}
