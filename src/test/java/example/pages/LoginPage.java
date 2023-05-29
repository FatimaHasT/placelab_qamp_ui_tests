package example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//input[@name='commit']");
    private final By errorMessage = By.cssSelector("div.span12 > div.error-area");

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
        WebElement emailField = driver.findElement(this.emailField);
        return emailField.isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        WebElement passwordField = driver.findElement(this.passwordField);
        return passwordField.isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(this.emailField);
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(this.passwordField);
        passwordField.sendKeys(password);
    }

    public void submitLoginForm() {
        WebElement submitButton = driver.findElement(loginButton);
        submitButton.click();
    }

    public String getErrorMessage() {
        WebElement errorMessage = driver.findElement(this.errorMessage);
        return errorMessage.getText();
    }
}

