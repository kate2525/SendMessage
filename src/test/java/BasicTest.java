import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BasicTest extends BeforeAfterClass {

    MailPage mailPage;

    @Test
    public void init() {
        LogInPage avt = new LogInPage(driver, driverWait);
        avt
                .avtorization()
                .sendMessage();

        mailPage = new MailPage(driver, driverWait);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mailPage.isDeliveryDisplay(), "Verify that email was delivered");
        softAssert.assertEquals(mailPage.getVerifyTextBody().getText(), MailPage.bodyText, "Verify the text 'Hello, world!'");
        softAssert.assertAll();
    }
}
