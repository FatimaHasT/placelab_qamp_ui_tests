package org.example.test;

import org.example.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InvalidEmailLoginTest {
    private WebDriver driver;
    private LoginPages loginPage;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        loginPage = new LoginPages(driver);
    }

    @Parameters("password")
    @Test
    public void testInvalidEmailLogin(final String password) {
        Assert.assertEquals(loginPage.getPageTitle(), "PlaceLab");

        Assert.assertTrue(loginPage.isHeaderDisplayed());

        Assert.assertTrue(loginPage.isLoginFormDisplayed());
        Assert.assertTrue(loginPage.isEmailFieldDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());

        loginPage.enterEmail("blabla@gmail.com");
        loginPage.enterPassword(password);

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

