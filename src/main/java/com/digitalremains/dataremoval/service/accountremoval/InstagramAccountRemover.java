package com.digitalremains.dataremoval.service.accountremoval;

import org.openqa.selenium.WebDriver;

public class InstagramAccountRemover implements AccountRemover {
    final String SERVICE_URL = "https://help.instagram.com/264154560391256/";

    @Override
    public void removeAccount() {
        final WebDriver driver = initializeDriver(SERVICE_URL);
        System.out.println(driver.getTitle());
    }
}
