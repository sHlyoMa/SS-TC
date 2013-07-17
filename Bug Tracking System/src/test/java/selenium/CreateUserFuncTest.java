package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateUserFuncTest {
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
		    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
		    driver.findElement(By.cssSelector("input.btn.btn-warning")).click();
		    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
		    driver.findElement(By.id("login")).clear();
		    driver.findElement(By.id("login")).sendKeys("aaa333");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("aaa333");
		    driver.findElement(By.id("userInfo.firstName")).clear();
		    driver.findElement(By.id("userInfo.firstName")).sendKeys("a");
		    driver.findElement(By.id("userInfo.secondName")).clear();
		    driver.findElement(By.id("userInfo.secondName")).sendKeys("a");
		    driver.findElement(By.id("userInfo.email")).clear();
		    driver.findElement(By.id("userInfo.email")).sendKeys("w@etert.ru");
		    new Select(driver.findElement(By.id("roleId"))).selectByVisibleText("Project manager");
		    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
		    driver.findElement(By.linkText("Log out")).click();
		    driver.findElement(By.name("username")).clear();
		    driver.findElement(By.name("username")).sendKeys("aaa333");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("aaa333");
		    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
		    driver.findElement(By.linkText("Projects")).click();
		    driver.findElement(By.linkText("Log out")).click();
		    driver.findElement(By.name("username")).clear();
		    driver.findElement(By.name("username")).sendKeys("admin1");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("admin1");
		    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
		    driver.findElement(By.cssSelector("i.icon-remove")).click();
		    driver.findElement(By.id("urlToRemoveAction")).click();

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
