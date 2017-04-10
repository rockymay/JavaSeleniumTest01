package automationFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);

		if (os.contains("windows")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		}
		else{
			
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
		}
		
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("http://www.google.com");
		driver.manage().window().maximize();
		driver.findElement(By.name("q")).sendKeys("Industry Connect");
		driver.findElement(By.name("btnK")).click();
		Thread.sleep(5000);
		driver.close();
	}

}
