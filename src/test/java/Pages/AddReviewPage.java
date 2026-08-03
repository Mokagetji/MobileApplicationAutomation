package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class AddReviewPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public AddReviewPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));//implicit wait because it does not wait for a specific condition

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By profileAvatorNativeLocator = By.xpath("//android.view.View[@content-desc='TC']");
    private By profileAvatorWebLocator = By.xpath("//button[@title='My Profile']");

    private By addReviewButtonNativeLocator = By.xpath("//android.widget.Button[@content-desc='+ Add Review']");
    private By addReviewButtonWebLocator = By.xpath("//button[normalize-space()='+ Add Review']");

    private By reviewTitleTextBoxNativeLocator = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private By reviewTitleTextBoxWebLocator = By.xpath("//input[@placeholder='E.g., Great Learning Experience!']");

    private By experienceTextBoxNativeLocator = By.xpath("");
    private By experienceTextBoxWebLocator = By.xpath("//label[normalize-space()='Your Experience']/following-sibling::textarea");


    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType");

        if (execType.equalsIgnoreCase("nativeApp")) {
            return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return wait.until((ExpectedConditions.elementToBeClickable(webLocator)));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }

    }

    public void clickProfileAvator() {
        WebElement avator = getElement(profileAvatorNativeLocator, profileAvatorWebLocator);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", avator);

    }

    public void clickAddReviewButton() {

        WebElement addReviewButton = getElement(addReviewButtonNativeLocator, addReviewButtonWebLocator);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600);");
        js.executeScript("arguments[0].click();", addReviewButton);

    }

    public void enterReviewTitle(String title) {

        WebElement reviewTitle = getElement(reviewTitleTextBoxNativeLocator, reviewTitleTextBoxWebLocator);
        reviewTitle.click();
        reviewTitle.sendKeys(title);
        System.out.println("Value = " + reviewTitle.getAttribute("value"));
    }

    public void selectReviewRating(int stars)
    {
        By reviewRatingWebLocator = By.xpath("//label[normalize-space()='Rating']/following-sibling::div/button[" + stars + "]");
        getElement(null,reviewRatingWebLocator).click();
    }

    public void enterYourExperience(String experience)
    {
        WebElement yourExperience = getElement(experienceTextBoxNativeLocator,experienceTextBoxWebLocator);
        yourExperience.click();
        yourExperience.sendKeys(experience);
        System.out.println("Value = " + yourExperience.getAttribute("value"));
    }


    }



