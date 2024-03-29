package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AccessDeniedTest {
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

	@Test
	public void test() throws Exception {
		driver.get(baseUrl + "/BugTrackingSystem/");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin1");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin1");
		driver.findElement(By.cssSelector("button.btn.btn-info")).click();
		driver.get(baseUrl + "/BugTrackingSystem/issue");
		driver.findElement(By.linkText("Back")).click();
		driver.findElement(By.linkText("Administrator")).click();
		driver.findElement(By.linkText("Project manager")).click();
		driver.findElement(By.linkText("Product owner")).click();
		driver.findElement(By.linkText("Developer")).click();
		driver.findElement(By.linkText("QC")).click();
		driver.findElement(By.linkText("All users")).click();
		driver.findElement(By.linkText("Log out")).click();
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
