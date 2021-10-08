package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BasePage {
    private long defaultMaxWaitTimeInSecs = 30;
    private long defaultMaxSleepInMilliSec = 200;

    private Wait<WebDriver> getDefaultWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(defaultMaxWaitTimeInSecs))
                .pollingEvery(Duration.ofMillis(defaultMaxSleepInMilliSec))
                .ignoring(NotFoundException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) throws Throwable {
        Wait<WebDriver> fluentWait = getDefaultWait(driver);
        return fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) throws Throwable {
        Wait<WebDriver> fluentWait = getDefaultWait(driver);
        return fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
