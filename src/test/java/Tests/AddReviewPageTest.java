package Tests;

import Base.BaseTest;
import org.testng.annotations.Test;

public class AddReviewPageTest extends BaseTest
{
    @Test
    public void addReviewNavigationTest() {

        addReviewPage.clickProfileAvator();

        addReviewPage.clickAddReviewButton();

        addReviewPage.enterReviewTitle("Happy Learning Experience");

        addReviewPage.selectReviewRating(3);

        addReviewPage.enterYourExperience("The best of the best");

    }


}
