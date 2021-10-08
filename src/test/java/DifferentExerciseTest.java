import org.testng.annotations.Test;
import pageobjects.HomePage;

public class DifferentExerciseTest extends BaseTest {

    @Test(description = "QA Automation Challenge for Quality Engineers")
    public void QaAutomationChallengeForQualityEngineers() throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.verifyHomePageDisplayed().changeUserName("Test").dismissDevUserAlert().selectImportantFeatures()
                .selectPrimaryOS().selectTestCafeInterface("JavaScript API").selectTriedTestCafe()
                .sliderTestScaleToFive()
                .addComment("Test comment").clickSubmitButton()
                .verifySuccessMessageDisplayed("Thank you, Peter Parker!");
        testReport.pass("Registration and checkout passed");
    }
}
