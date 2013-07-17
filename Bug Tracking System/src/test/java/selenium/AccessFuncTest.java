package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AccessFuncTest {
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
	    driver.findElement(By.id("login")).clear();
	    driver.findElement(By.id("login")).sendKeys("zzzzzz");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("zzzzzz");
	    driver.findElement(By.id("userInfo.firstName")).clear();
	    driver.findElement(By.id("userInfo.firstName")).sendKeys("z");
	    driver.findElement(By.id("userInfo.secondName")).clear();
	    driver.findElement(By.id("userInfo.secondName")).sendKeys("z");
	    driver.findElement(By.id("userInfo.email")).clear();
	    driver.findElement(By.id("userInfo.email")).sendKeys("op;oip@f.rs+");
	    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("zzzzzz");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("zzzzzz");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin1");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin1");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.xpath("//table[@id='main-table']/tbody/tr[2]/td/div/div/div[2]/div/div[4]/table/tbody/tr[18]/td[7]/a/i")).click();
	    new Select(driver.findElement(By.id("roleId"))).selectByVisibleText("God mode");
	    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("zzzzzz");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("zzzzzz");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.linkText("Roles")).click();
	    driver.findElement(By.linkText("Users")).click();
	    driver.findElement(By.linkText("Projects")).click();
	    driver.findElement(By.linkText("Issues")).click();
	    driver.findElement(By.linkText("Language")).click();
	    driver.findElement(By.linkText("English")).click();
	    driver.findElement(By.linkText("Language")).click();
	    driver.findElement(By.linkText("Русский")).click();
	    driver.findElement(By.linkText("Язык")).click();
	    driver.findElement(By.linkText("Українська")).click();
	    driver.findElement(By.linkText("Мова")).click();
	    driver.findElement(By.linkText("English")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin1");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin1");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    new Select(driver.findElement(By.id("roleId"))).selectByVisibleText("Developer");
	    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("zzzzzz");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("zzzzzz");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.linkText("Projects")).click();
	    driver.findElement(By.linkText("Issues")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin1");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin1");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.xpath("//table[@id='main-table']/tbody/tr[2]/td/div/div/div[2]/div/div[4]/table/tbody/tr[18]/td[8]/a/i")).click();
	    driver.findElement(By.id("urlToRemoveAction")).click();
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
