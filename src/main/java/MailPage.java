import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MailPage extends AbstractPage {

    @FindBy(xpath = "//*[contains(text(),'НАПИСАТЬ')]")
    private WebElement writeBtn;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement toField;

    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    private WebElement bodyField;

    @FindBy(xpath = "//*[contains(text(),'Отправить')]")
    private WebElement sendBtn;

    @FindBy(xpath = "//a[contains(text(),'Входящие')]")
    private WebElement goToInbox;

    static String bodyText = "Hello world!";

    public MailPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void sendMessage() {
        writeBtn.click();
        inputEmail();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        goToInbox.click();
    }

    private void inputEmail() {
        toField.sendKeys(LogInPage.email);
        bodyField.sendKeys(bodyText);
        clickOnInvisibleElement(sendBtn);
    }

    private List<WebElement> getDeliveryDesplay() {
        return driver.findElements(By.xpath("//div[@class='UI']//tbody/tr"));
    }

    public boolean isDeliveryDisplay() {
        return getDeliveryDesplay().get(0).isDisplayed();
    }

    public WebElement getVerifyTextBody() {
        getDeliveryDesplay().get(0).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver.findElement(By.xpath("//div[@role='listitem']//div[contains(text(),'Hello world!')]"));
    }
}
