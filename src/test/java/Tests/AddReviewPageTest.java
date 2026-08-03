package Tests;

import Base.BaseTest;
import Utilities.ScreenshotUtilities;
import org.testng.annotations.Test;

public class AddReviewPageTest extends BaseTest
{
    @Test
    public void addReviewNavigationTest() {

        addReviewPage.clickProfileAvator();
        ScreenshotUtilities.captureScreenshot(driver,"Profile Avator Clicked");

        addReviewPage.clickAddReviewButton();
        ScreenshotUtilities.captureScreenshot(driver,"Add Review Button Clicked");

        addReviewPage.enterReviewTitle("Happy Learning Experience");
        ScreenshotUtilities.captureScreenshot(driver,"Review Title Added");

        addReviewPage.selectReviewRating(4);
        ScreenshotUtilities.captureScreenshot(driver,"Review Rating Selected");

        addReviewPage.enterYourExperience("The best of the best");
        ScreenshotUtilities.captureScreenshot(driver,"Experience Added");

    }


}
