package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SuccessPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "article-header")
    private WebElement headerSuccess;

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SuccessPage verifySuccessMessageDisplayed(String headerText) throws Throwable {
        waitForElementToBeVisible(driver,headerSuccess);
        String hdrText = headerSuccess.getText();
        Assert.assertTrue(hdrText.contains(headerText),"header text is not matching actual : "+ hdrText+" , Expected : "+headerText);
        return this;
    }

}
