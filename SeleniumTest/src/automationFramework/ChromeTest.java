package automationFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		/*
		driver.navigate().to("http://www.google.com");
		driver.manage().window().maximize();
		driver.findElement(By.name("q")).sendKeys("Industry Connect");
		driver.findElement(By.name("btnK")).click();
		Thread.sleep(5000);
		//driver.close();
		*/
		String usernameInput = "Jiya";
		String passwordInput = "Jiya@345";

        
        //Define IWebDriver
        driver.navigate().to("https://demo.econz.co.nz:1000/AdminPortal/Account/Login/exptest");
        driver.manage().window().maximize();

        //####Login Test Here #####
        //define user name and password and login 
        WebElement Username = driver.findElement(By.id("UserName"));
        WebElement Password = driver.findElement(By.id("Password"));
        WebElement LoginButton = driver.findElement(By.xpath("/html/body/div[3]/form/div/div/div/div[2]/div[3]/input"));

        //Assign Value to User name/Password and Login Verification
        Username.sendKeys(usernameInput);
        Password.sendKeys(passwordInput);

        String ExpectedMessage = "Welcome";

        //Handle Exception for Login Verification
        LoginButton.click();
        String LoginMessage = driver.findElement(By.xpath("//*[@id='container']/div/div/h2")).getText();
        if (LoginMessage == ExpectedMessage)
        { System.out.println("Login successfully"); }
        

        //Navigate to Interface --> Buttons Page
        driver.findElement(By.cssSelector("#menu-items > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.cssSelector("#menu-items > ul > li.dropdown.open > ul > li:nth-child(3) > a")).click();

        
        driver.findElement(By.xpath("//*[@id='grid']/div[1]/a")).click();

        //TextBox input
        driver.findElement(By.name("BTN_NAME")).sendKeys("");
        driver.findElement(By.name("BTN_DISPLAY_TITLE")).sendKeys("");
        driver.findElement(By.name("PRE_CONDITION")).sendKeys("");
        driver.findElement(By.name("VALUE_UPDATES")).sendKeys("");
        Thread.sleep(3000);
        driver.close();
		
	}

}
