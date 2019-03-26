import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static java.util.concurrent.TimeUnit.SECONDS;


public class Test {

	final static WebDriver driver = Driver.getChromeDriver();
	final static String URL = "https://passport.yandex.ru/";
	final static String INPUT_NAME = "//*[@id=\"passp-field-login\"]";
	final static String BUTTON = "//button[1]";
	final static String INPUT_PAS = "//*[@id=\"passp-field-passwd\"]";
	final static String TEXT = "//*[@class=\"personal-info-login personal-info-login__displaylogin\"]";
	final static String INPUT_PAS_ERROR = "//*[@class=\"passp-form-field__error\"]";

	@BeforeClass
	public static void setup() {
		driver.get(URL);
	}

	//тест проверяет авториизацию в систему в яндекс паспорт. Для проверки создан пользователь с логином galiyakh пароль !q2w3e4
	@org.junit.Test
	public void login_positive(){

		WebElement name = driver.findElement(By.xpath(INPUT_NAME));
		name.sendKeys("galiyakh");
		WebElement button = driver.findElement(By.xpath(BUTTON));
		button.click();

		//ждем загрузку 10 сек
		driver.manage().timeouts().implicitlyWait(10,SECONDS);
		WebElement pas = driver.findElement(By.xpath(INPUT_PAS));
		pas.sendKeys("!q2w3e4");
		WebElement button_ = driver.findElement(By.xpath(BUTTON));
		button_.click();

		//ждем загрузку 10 сек
		driver.manage().timeouts().implicitlyWait(10,SECONDS);
		WebElement text = driver.findElement(By.xpath(TEXT));
		String login_name = text.getText();
		//считаем, что если появилась строка с содержанием ниже, то мы успешно прошли авторизацию
		Assert.assertEquals("galiyakh", login_name);
		System.out.println("Позитивный тест пройден. Успешная авторизация в систему\n");
	}

	@org.junit.Test
	public void login_negative(){

		driver.get(URL);
		WebElement name = driver.findElement(By.xpath(INPUT_NAME));
		name.sendKeys("galiyakh");
		WebElement button = driver.findElement(By.xpath(BUTTON));
		button.click();

		//ждем загрузку 10 сек
		driver.manage().timeouts().implicitlyWait(10,SECONDS);
		WebElement pas = driver.findElement(By.xpath(INPUT_PAS));
		pas.sendKeys("124");
		WebElement button_ = driver.findElement(By.xpath(BUTTON));
		button_.click();

		driver.manage().timeouts().implicitlyWait(10,SECONDS);
		WebElement pas_err = driver.findElement(By.xpath(INPUT_PAS_ERROR));
		String text_err = pas_err.getText();

		//считаем, что если появилась строка с содержанием ниже, то мы успешно прошли негативный тест на проверку пустого поля
		Assert.assertEquals("Неверный пароль", text_err);
		System.out.println("Негативный тест пройден. Неверный пароль, поэтому авторизация не прошла\n");
	}

	@AfterClass
	public static void killDriver(){
		driver.close();
	}
}
