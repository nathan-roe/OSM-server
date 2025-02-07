package com.digitalremains.dataremoval.service.accountremoval;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class GoogleAccountRemover implements AccountRemover {
    final String SERVICE_URL = "https://support.google.com/accounts/troubleshooter/6357590?hl=en#ts=6357652";
    final WebDriver driver;

    public GoogleAccountRemover() {
        this.driver  = initializeDriver(SERVICE_URL);
    }
    @Override
    public void removeAccount() {
        try {
            final WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            final WebElement deceasedFullName = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(@id, '--name_deceased')]")
            ));
            deceasedFullName.sendKeys("test");
            final WebElement deceasedEmailAddress = driver.findElement(By.xpath("//*[contains(@id, '--email_address_deceased')]"));
            deceasedEmailAddress.sendKeys("test");

            final WebElement representativeFullName = driver.findElement(By.xpath("//*[contains(@id, '--full_name_req')]"));
            representativeFullName.sendKeys("test");
            final WebElement representativeEmailAddress = driver.findElement(By.xpath("//*[contains(@id, '--ContactEmail')]"));
            representativeEmailAddress.sendKeys("test");
            driver.findElement(By.xpath("//*[contains(@id, '--relationship_deceased--representative')]"))
                    .findElement(By.xpath("./.."))
                    .click();

            final JavascriptExecutor jse = (JavascriptExecutor) driver;
            driver.findElement(By.className("sc-select")).click();
            final WebElement countrySelect = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sc-select-show")));
            jse.executeScript("arguments[0].scrollIntoView({block: 'end'})", countrySelect);
            final WebElement countryOfOrigin = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(":302")));
            countryOfOrigin.click();

            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(@id, '--us_state')]")
            )).findElement(By.xpath("./..")).findElement(By.className("sc-select")).click();
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(":315"))).click();

            driver.findElement(By.xpath("//*[contains(@id, '--zipcode')]")).sendKeys("test");
            driver.findElement(By.xpath("//*[contains(@id, '--dateofdeath')]")).sendKeys("12/08/2024");

            driver.findElement(By.xpath("//*[contains(@id, '--products--accounts')]"))
                    .findElement(By.xpath("../.."))
                    .click();
            driver.findElement(By.id("govt_id_or_drivers_license")).sendKeys(getGovernmentIdOrLicense().getAbsolutePath());

            final WebElement deathCertificateUpload = driver.findElement(By.id("death_certificate"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(
                    deathCertificateUpload.findElement(By.xpath("./.."))
            ));
            deathCertificateUpload.sendKeys(getDeathCertificate().getAbsolutePath());

            System.out.println(driver.getTitle());
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
