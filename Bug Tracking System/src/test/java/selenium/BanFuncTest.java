package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BanFuncTest {
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
	    driver.findElement(By.id("login")).sendKeys("aaasss");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("aaasss");
	    driver.findElement(By.id("userInfo.firstName")).clear();
	    driver.findElement(By.id("userInfo.firstName")).sendKeys("a");
	    driver.findElement(By.id("userInfo.secondName")).clear();
	    driver.findElement(By.id("userInfo.secondName")).sendKeys("a");
	    driver.findElement(By.id("userInfo.email")).clear();
	    driver.findElement(By.id("userInfo.email")).sendKeys("w@etert.ru");
	    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
	    driver.findElement(By.xpath("//table[@id='main-table']/tbody/tr[2]/td/div/div/div[2]/div/div[4]/table/tbody/tr[2]/td[6]/div/div/span")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("aaasss");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("aaasss");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin1");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin1");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.xpath("//table[@id='main-table']/tbody/tr[2]/td/div/div/div[2]/div/div[4]/table/tbody/tr[2]/td[6]/div/div/span[2]")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("aaasss");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("aaasss");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin1");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin1");
	    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	    driver.findElement(By.cssSelector("input.btn.btn-info")).click();
	    driver.findElement(By.cssSelector("input.btn.btn-warning")).click();
	    driver.findElement(By.xpath("//table[@id='main-table']/tbody/tr[2]/td/div/div/div[2]/div/div[4]/table/tbody/tr[2]/td[8]/a/i")).click();
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
