package com.digitalremains.dataremoval.service.accountremoval;

import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface AccountRemover {
    void removeAccount();

    default WebDriver initializeDriver(final String serviceUrl) {
        final NativeWebDriver nativeWebDriver = new NativeWebDriver();
        final WebDriver driver = nativeWebDriver.getWebDriver();
        driver.get(serviceUrl);
        return driver;
    }

    default File getDeathCertificate() throws IOException {
        final File deathCertificateFile = File.createTempFile("death-certificate-",  ".txt");
        deathCertificateFile.deleteOnExit();
        final BufferedWriter writer = new BufferedWriter(new FileWriter(deathCertificateFile));
        writer.write("test");
        writer.close();
        return deathCertificateFile;
    }

    default File getGovernmentIdOrLicense() throws IOException {
        final File governmentId = File.createTempFile("government-id-",  ".txt");
        governmentId.deleteOnExit();
        final BufferedWriter writer = new BufferedWriter(new FileWriter(governmentId));
        writer.write("test");
        writer.close();
        return governmentId;
    }
}
