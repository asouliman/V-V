package mdms;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppearanceTest extends AbstractMDMSTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Tests if the page title is "MdMS - Diversify"
     * @throws Exception
     */
    @Test
    public void testPageTitle() throws Exception {
        driver.get(baseUrl + "/");
        assertEquals("MdMS - Diversify", driver.getTitle());
    }

    /**
     * Tests if the username is visible once the user logged in
     * @throws Exception
     */
    @Test
    public void testUsernamePresent() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        assertEquals("admin", driver.findElement(By.linkText("admin")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
