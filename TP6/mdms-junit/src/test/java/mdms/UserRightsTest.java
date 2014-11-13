package mdms;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserRightsTest extends AbstractMDMSTest {

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
     * Tests if it is possible to log in
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        driver.get(baseUrl + "/");
        assertTrue(isElementPresent(By.cssSelector("button.btn.btn-success")));
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        assertTrue(isElementPresent(By.linkText("Sign out")));
    }

    /**
     * Tests if we can log in with the wrong password
     * @throws Exception
     */
    @Test
    public void testWrongPassword() throws Exception {
        driver.get(baseUrl + "/");
        assertTrue(isElementPresent(By.cssSelector("button.btn.btn-success")));
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admi");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        assertFalse(isElementPresent(By.linkText("Sign out")));
    }

    /**
     * Tests if we can log in with the wrong login
     * @throws Exception
     */
    @Test
    public void testWrongLogin() throws Exception {
        driver.get(baseUrl + "/");
        assertTrue(isElementPresent(By.cssSelector("button.btn.btn-success")));
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admi");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        assertFalse(isElementPresent(By.linkText("Sign out")));
        driver.findElement(By.xpath("(//a[contains(text(),'Edit')])")).click();
        assertEquals("http://localhost:8080/", driver.getCurrentUrl());
    }

    /**
     * Tests if we can edit an article without being logged in
     * @throws Exception
     */
    @Test
    public void testEditable() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("(//a[contains(text(),'Edit')])")).click();
        assertEquals("http://localhost:8080/", driver.getCurrentUrl());
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
