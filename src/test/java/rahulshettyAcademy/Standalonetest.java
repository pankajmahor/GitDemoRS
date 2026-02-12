package rahulshettyAcademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyAcademy.PageObjects.LandingPage;

public class Standalonetest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		// WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTO(); // driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();

		/*
		 * driver.findElement(By.id("userEmail")).sendKeys("sa.johngrey@gmail.com");
		 * driver.findElement(By.id("userPassword")).sendKeys("Test@4321");
		 * driver.findElement(By.id("login")).click();
		 */ // for above commented code use below single line

		landingpage.LoginApplication("sa.johngrey@gmail.com", "Test@4321");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	}

}
