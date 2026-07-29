package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Properties;


public class DashboardPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public DashboardPage(AppiumDriver driver, Properties config){
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));//implicit wait because it does not wait for a specific condition

        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    //private By viewAllButtonNativeLocator = By.xpath("//android.widget.Button[@content-desc='View All →']");
    //private By viewAllButtonWebLocator = By.xpath("//button[contains(normalize-space(),'View All')]");


    private WebElement getElement(By nativeLocator, By webLocator)
    {
        String execType = config.getProperty("executionType");

        if (execType.equalsIgnoreCase("nativeApp"))
        {
            return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        }
        else if (execType.equalsIgnoreCase("mobileWeb"))
        {
            return wait.until((ExpectedConditions.elementToBeClickable(webLocator)));
        }
        else
        {
            throw new RuntimeException("Unsupported executionType: "+execType);
        }

    }

    public void clickViewAllButton()
    {
        //getElement(viewAllButtonNativeLocator,viewAllButtonWebLocator).click();
    }
}
