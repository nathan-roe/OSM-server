package com.digitalremains.dataremoval.service.accountremoval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class FacebookAccountRemover implements AccountRemover {
    final String SERVICE_URL = "https://www.facebook.com/help/contact/228813257197480";
    final WebDriver driver;

    public FacebookAccountRemover() {
        this.driver = initializeDriver(SERVICE_URL);
    }

    @Override
    public void removeAccount() {
        try {
            final WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            final WebElement reporterNameField = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("330310033654312")
            ));
            reporterNameField.sendKeys("test");
            final WebElement emailField = driver.findElement(By.id("505577623251506"));
            emailField.sendKeys("test");
            final WebElement fullProfileName = driver.findElement(By.id("210052215752699"));
            fullProfileName.sendKeys("test");
            final WebElement profileUrl = driver.findElement(By.id("148162038628949"));
            profileUrl.sendKeys("test");
            final WebElement deceasedUserEmail = driver.findElement(By.id("326332420720111"));
            deceasedUserEmail.sendKeys("test");
            final WebElement removeDeceasedAccountRadio = driver.findElement(By.id("578598558824543.1"))
                    .findElement(By.xpath("../.."));
            removeDeceasedAccountRadio.click();
            final WebElement understandDeathCertificateUpload = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("486521288817877.0")
            )).findElement(By.xpath("./.."));
            understandDeathCertificateUpload.click();
            final WebElement deathCertificateUpload = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("324966381716113")
            )).findElement(By.tagName("input"));

            deathCertificateUpload.sendKeys(getDeathCertificate().getAbsolutePath());
            final WebElement dateOfDeath = driver.findElement(By.id("SupportFormRow.118411538623716"))
                    .findElement(By.tagName("input"));
            dateOfDeath.sendKeys("12/08/2024");

            System.out.println(driver.getTitle());
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
