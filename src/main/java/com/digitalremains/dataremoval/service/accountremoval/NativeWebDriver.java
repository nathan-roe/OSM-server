package com.digitalremains.dataremoval.service.accountremoval;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class NativeWebDriver {
    private static final String DEFAULT_DRIVER_PROPERTY = "webdriver.chrome.driver";

    @Value("${webdriver.driver.path}")
    private String webDriverPath;
    @Getter
    private WebDriver webDriver;
    private String webDriverProperty = DEFAULT_DRIVER_PROPERTY;

    public NativeWebDriver() {
        initializeNativeWebDriver();
    }

    public NativeWebDriver(final WebDriver webDriver, final String webDriverProperty) {
        initializeNativeWebDriver();
        this.webDriver = webDriver;
        this.webDriverProperty = webDriverProperty;
    }

    private void initializeNativeWebDriver() {
        if(!System.getProperties().containsKey(webDriverProperty)) {
            log.info("Setting the web drive property to: {}", webDriverProperty);
            System.setProperty(webDriverProperty, webDriverPath);
        }
        final ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        this.webDriver = new ChromeDriver(options);
    }

}
