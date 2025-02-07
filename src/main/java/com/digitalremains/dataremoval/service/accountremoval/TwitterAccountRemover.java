package com.digitalremains.dataremoval.service.accountremoval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TwitterAccountRemover implements AccountRemover {
    final String SERVICE_URL = "https://help.x.com/en/forms/account-access/deactivate-or-close-account/deactivate-account-for-deceased";
    final WebDriver driver;

    public TwitterAccountRemover() {
        driver = initializeDriver(SERVICE_URL);
    }
    @Override
    public void removeAccount() {
        try {
            final WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            final WebElement deceasedUserRadio = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@name='Category__c']")
            )).findElement(By.xpath("./.."));
            deceasedUserRadio.click();

            final WebElement deceasedUserFullName = driver.findElement(By.xpath("//input[@name='Form_Name__c']"));
            deceasedUserFullName.sendKeys("test");
            final WebElement deceasedUserEmail = driver.findElement(By.xpath("//input[@name='Form_Email__c']"));
            deceasedUserEmail.sendKeys("test");
            final WebElement relationshipToUserRadio = driver.findElement(
                    By.xpath("//input[@name='Relationship_to_User__c']")
            ).findElement(By.xpath("../.."));
            relationshipToUserRadio.click();
            final WebElement deceasedUserName = driver.findElement(By.xpath("//input[@name='Reported_Screen_Name__c']"));
            deceasedUserName.sendKeys("test");
            final WebElement deceasedFullName = driver.findElement(By.xpath("//input[@name='owner-full-name']"));
            deceasedFullName.sendKeys("test");
        } finally {
            driver.quit();
        }
    }
}
