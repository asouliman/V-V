package mdms;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditingTest extends AbstractMDMSTest {

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
     * Tests if we can add an article and delete one
     * @throws Exception
     */
    @Test
    public void testEditing() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("Add article")).click();
        String titleContent = "yoyo";
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys(titleContent);
        String textContent = "# H1";
        driver.findElement(By.cssSelector("div > textarea")).clear();
        driver.findElement(By.cssSelector("div > textarea")).sendKeys(textContent);
        assertTrue(isElementPresent(By.xpath("//div[@id=\"out\"]/h1")));
        driver.findElement(By.id("save")).click();
        assertTrue(isElementPresent(By.xpath("//div[contains(@class, 'article')]/h2[contains(text(), '" + titleContent + "')]")));
        Number nbArticlesBefore = driver.findElements(By.xpath("//div[contains(@class, 'article')]")).size();
        driver.findElement(By.xpath("//div[contains(@class, 'article')]/h2[contains(text(), '" + titleContent + "')]/a[contains(text(), 'Delete')]")).click();
        Number nbArticlesAfter = driver.findElements(By.xpath("//div[contains(@class, 'article')]")).size();
        assertEquals(nbArticlesAfter, nbArticlesBefore.intValue()-1);
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
