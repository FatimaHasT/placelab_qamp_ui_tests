package org.example.test;

import org.example.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ValidLoginTest {
    private WebDriver driver;
    private LoginPages loginPage;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        loginPage = new LoginPages(driver);
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
        final String actualRole = driver.findElement(By.id("user-role")).getText();
        Assert.assertEquals(actualRole, expectedRole, "Validate user role for logged in user.");

        driver.findElement(By.id("user-role")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Sign out")).isDisplayed());

        driver.findElement(By.linkText("Sign out")).click();

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
