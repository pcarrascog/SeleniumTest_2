package test.resources;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AboutFeature {
	public static String curDir = System.getProperty("user.dir");
	// private ChromeDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();

	@Before
	public void setUp() throws Exception {
		/*
		 * System.setProperty("webdriver.gecko.driver", curDir +
		 * "\\Drivers\\geckodriver.exe"); driver = new FirefoxDriver();
		 * 
		 * System.setProperty("webdriver.chrome.driver", curDir +
		 * "\\Drivers\\chrome.exe"); WebDriver driver = new ChromeDriver();
		 * 
		 * System.setProperty("webdriver.ie.driver", curDir +
		 * "\\Drivers\\IEDriverServer.exe"); driver = new
		 * InternetExplorerDriver();
		 * 
		 */
	}

	@Test
	public void testAboutFeature() throws Exception {
		/*
		 * System.setProperty("webdriver.chrome.driver", curDir +
		 * "\\Drivers\\chromedriver.exe");
		 */
		System.setProperty("webdriver.chrome.driver", curDir + "/Drivers/chromedriver");
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(curDir + "/Drivers/chromedriver")).usingAnyFreePort().build();
		try {
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RemoteWebDriver driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
		driver.manage().window().maximize();

		baseUrl = "http://192.168.17.223:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		int tiempoL = 1000;
		int tiempoML = 5000;
		Robot robot = new Robot();

		driver.get(baseUrl + "/cargo-tracker/");
		robot.delay(tiempoML);
		driver.findElement(By.linkText("Administration Interface")).click();
		robot.delay(tiempoL);
		driver.findElement(By.linkText("About")).click();
		robot.delay(tiempoL);
		assertEquals("This interface includes several features for Cargo Administrators:",
				driver.findElement(By.cssSelector("h3")).getText());
		robot.delay(tiempoL);
		driver.quit();
	}

	@After
	public void tearDown() throws Exception {
		
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
