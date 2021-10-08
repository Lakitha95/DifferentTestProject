package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.Console;
import java.util.List;
import java.util.logging.Logger;

public class HomePage extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "//h1[text()='Example']")
    private WebElement pageHeader;

    @FindBy(id = "developer-name")
    private WebElement txtDevName;

    @FindBy(id = "populate")
    private WebElement btnPopulate;

    @FindBy(id = "reusing-js-code")
    private WebElement chkReUse;

    @FindBy(id = "background-parallel-testing")
    private WebElement chkParallelTest;

    @FindBy(id = "tried-test-cafe")
    private WebElement chkTriedTestCafe;

    @FindBy(id = "windows")
    private WebElement rdoWindowsOS;

    @FindBy(id = "preferred-interface")
    private WebElement selectInterface;

    @FindBy(id = "slider")
    private WebElement divSlider;

    @FindBy(id = "comments")
    private WebElement txtComments;

    @FindBy(id = "submit-button")
    private WebElement btnSubmit;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage verifyHomePageDisplayed() throws Throwable {
        waitForElementToBeVisible(driver,pageHeader);
        Assert.assertTrue(pageHeader.isDisplayed(),"Home page not displayed");
        return this;
    }

    public HomePage changeUserName(String devName) throws Throwable {
        waitForElementToBeVisible(driver,txtDevName);
        txtDevName.sendKeys(devName);
        btnPopulate.click();
        Thread.sleep(2000);
        return this;
    }

    public HomePage dismissDevUserAlert() throws Throwable {
        driver.switchTo().alert().accept();
        System.out.println("alert dismissed");
        return this;
    }

    public HomePage selectImportantFeatures() throws Throwable {
        waitForElementToBeClickable(driver,chkReUse);
        chkReUse.click();
        waitForElementToBeClickable(driver,chkParallelTest);
        chkParallelTest.click();
        return this;
    }

    public HomePage selectPrimaryOS() throws Throwable {
        rdoWindowsOS.click();
        System.out.println("Windows OS selected as Primary OS");
        return this;
    }

    public HomePage selectTriedTestCafe() throws Throwable {
        chkTriedTestCafe.click();
        System.out.println("Selected Tried test cafe");
        return this;
    }

    public HomePage selectTestCafeInterface(String interfaceName) throws Throwable {
        Select interfaceSelect = new Select(selectInterface);
        interfaceSelect.selectByVisibleText(interfaceName);
        return this;
    }

    public HomePage sliderTestScaleToFive() throws Throwable {
        try{
            Actions slide = new Actions(driver);
            slide.dragAndDropBy(divSlider,708,654).perform();
            System.out.println("Slide up to 5");
        }catch (Exception ex){}
        return this;
    }

    public HomePage addComment(String commentText) throws Throwable {
        txtComments.sendKeys(commentText);
        return this;
    }

    public SuccessPage clickSubmitButton() throws Throwable {
        btnSubmit.click();
        return new SuccessPage(driver);
    }

}
