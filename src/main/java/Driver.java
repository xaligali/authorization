import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Driver {
	private static WebDriver driver;

	private Driver() {}

	public static WebDriver getChromeDriver() {
		if (driver == null) {
			// установка системной зависимости с указанием пути до специального драйвера
			System.setProperty("WebDriver.chrome.driver", "/chromedriver");
			driver = new ChromeDriver();
		}
		return driver;
	}
}