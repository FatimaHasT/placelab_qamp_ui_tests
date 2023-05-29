package example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    private final By userRole = By.id("user-role");
    private final By signOutLink = By.linkText("Sign out");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserRole() {
        WebElement roleElement = driver.findElement(userRole);
        return roleElement.getText();
    }

    public void clickUserRole() {
        WebElement roleElement = driver.findElement(userRole);
        roleElement.click();
    }

    public boolean isSignOutLinkDisplayed() {
        WebElement signOutLinkElement = driver.findElement(signOutLink);
        return signOutLinkElement.isDisplayed();
    }

    public void clickSignOutLink() {
        WebElement signOutLinkElement = driver.findElement(signOutLink);
        signOutLinkElement.click();
    }
}
