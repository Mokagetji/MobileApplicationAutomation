package Tests;

import Base.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    @Test
    public void viewMyCoursesTest()
    {
        dashboardPage.clickViewAllButton();
    }
}
