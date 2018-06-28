import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogInPage extends AbstractPage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//span[contains(text(),'Далее')]")
    private WebElement nextBtn;

    @FindBy(xpath = "//span[contains(text(),'Готово')]")
    private WebElement readyBtn;

    static String email = "ktestkati@gmail.com";
    String password = "010203040506AA";

    public LogInPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public MailPage avtorization() {
        inputEmail.sendKeys(email);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        nextBtn.click();
        inputPassword.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clickOnInvisibleElement(nextBtn);
        return new MailPage(driver, driverWait);
    }
}
