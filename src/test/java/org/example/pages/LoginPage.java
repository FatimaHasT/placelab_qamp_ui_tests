package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isHeaderDisplayed() {
        WebElement header = driver.findElement(By.cssSelector("div#login > p.headline"));
        return header.isDisplayed();
    }

    public boolean isLoginFormDisplayed() {
        WebElement loginForm = driver.findElement(By.id("login_form"));
        return loginForm.isDisplayed();
    }

    public boolean isEmailFieldDisplayed() {
        WebElement emailField = driver.findElement(By.id("email"));
        return emailField.isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        WebElement passwordField = driver.findElement(By.id("password"));
        return passwordField.isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    public void submitLoginForm() {
        WebElement submitButton = driver.findElement(By.xpath("//input[@name='commit']"));
        submitButton.click();
    }

    public String getErrorMessage() {
        WebElement errorMessage = driver.findElement(By.cssSelector("div.span12 > div.error-area"));
        return errorMessage.getText();
    }
}
