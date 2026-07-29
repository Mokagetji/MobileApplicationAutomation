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


public class LoginPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public LoginPage(AppiumDriver driver, Properties config)
    {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));//implicit wait because it does not wait for a specific condition

        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    private By burgerMenuButtonNativeLocator = By.xpath("//android.widget.Button");
    private By burgermenuButtonWebLocator = By.xpath("//button[contains(@class,'nav-burger')]");

    private By signInButtonNativeLocator = By.xpath("//android.widget.Button[@content-desc='Login / Sign Up']");
    private By signInButtonWebLocator = By.xpath("//span[normalize-space()='Login / Sign Up']");

    private By emailTextFieldNativeLocator = By.xpath("//android.widget.EditText[@hint='Email']");
    private By emailTextFieldWebLocator = By.id("login-email");

    private By passwordTextFieldNativeLocator = By.xpath("//android.widget.EditText[@hint='Password']");
    private By passwordTextFieldWebLocator = By.id("login-password");

    private By loginButtonNativeLocator = By.xpath("//android.widget.Button[@content-desc='Login']");
    private By loginButtonWebLocator = By.name("loginSubmit");

    //private By loginAssertionElementNativeLocator =;
    //private By loginAssertionElementWebLocator = ;


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

    public void clickBurgerMenuButton()
    {
        getElement(burgerMenuButtonNativeLocator, burgermenuButtonWebLocator).click();

    }

    public void clickSignInButton()
    {
        getElement(signInButtonNativeLocator, signInButtonWebLocator).click();
    }

    public void enterEmail(String email)
    {
        WebElement emailElement = getElement(emailTextFieldNativeLocator,emailTextFieldWebLocator);
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password)
    {
        WebElement passwordElement = getElement(passwordTextFieldNativeLocator,passwordTextFieldWebLocator);
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() throws InterruptedException {
        getElement(loginButtonNativeLocator,loginButtonWebLocator).click();
        Thread.sleep(5000);
    }



}
