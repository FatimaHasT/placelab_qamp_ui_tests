package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUserRole() {
        WebElement userRole = driver.findElement(By.id("user-role"));
        return userRole.getText();
    }

    public void clickUserRole() {
        WebElement userRole = driver.findElement(By.id("user-role"));
        userRole.click();
    }

    public boolean isSignOutLinkDisplayed() {
        WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
        return signOutLink.isDisplayed();
    }

    public void clickSignOutLink() {
        WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
        signOutLink.click();
    }
}
