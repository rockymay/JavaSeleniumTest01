package automationFramework;
import java.util.*;

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
		List<String> addNewRecord = Arrays.asList("sup1", "sup2", "sup3", "sup4");
		List<String> editRecord = Arrays.asList("Editsup1", "Editsup2", "Editsup3", "Editsup4");
        
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
        driver.findElement(By.name("BTN_NAME")).sendKeys(addNewRecord.get(0));
        driver.findElement(By.name("BTN_DISPLAY_TITLE")).sendKeys(addNewRecord.get(1));
        driver.findElement(By.name("PRE_CONDITION")).sendKeys(addNewRecord.get(2));
        driver.findElement(By.name("VALUE_UPDATES")).sendKeys(addNewRecord.get(3));
        Thread.sleep(3000);
        
        DropdownSelect(driver);
        
        driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[27]/a[1]")).click();
        driver.switchTo().alert().dismiss();
        
        driver.navigate().refresh();
        //Use VerifyResult to Verify Record Change
        VerifyResult(driver, addNewRecord, "Add New Record", "AddUpdate");
        
        
      //########Edit Button Test From Here########
        
        //Select the New Record
        Filter(driver, addNewRecord.get(0));
        driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[14]/a[1]")).click();


        //Text box Update
        driver.findElement(By.name("BTN_NAME")).clear();
        driver.findElement(By.name("BTN_DISPLAY_TITLE")).clear();
        driver.findElement(By.name("PRE_CONDITION")).clear();
        driver.findElement(By.name("VALUE_UPDATES")).clear();
        driver.findElement(By.name("BTN_NAME")).sendKeys(editRecord.get(0));
        driver.findElement(By.name("BTN_DISPLAY_TITLE")).sendKeys(editRecord.get(1));
        driver.findElement(By.name("PRE_CONDITION")).sendKeys(editRecord.get(2));
        driver.findElement(By.name("VALUE_UPDATES")).sendKeys(editRecord.get(3));


        driver.findElement(By.cssSelector("body > div.k-widget.k-window > div.k-popup-edit-form.k-window-content.k-content > div > div.k-edit-buttons.k-state-default > a.k-button.k-button-icontext.k-grid-update")).click();
    


        VerifyResult(driver, editRecord, "Edit Existing Record", "AddUpdate");


      //######## Delete Function Test Here#########
        
        Filter(driver, editRecord.get(0));
        
      
        //Save Selected Record into deleteRecord 
        List<String> deleteRecord = new ArrayList<String>();

        deleteRecord.add(driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[1]")).getText());
        deleteRecord.add(driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[2]")).getText());
        deleteRecord.add(driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[4]")).getText());
        deleteRecord.add(driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[6]")).getText());
        System.out.println("You are trying to delet button: "+  "/n" + deleteRecord.get(0)+ "/n" + deleteRecord.get(1) +"/n" + deleteRecord.get(2) +"/n"  +deleteRecord.get(3));
        
        driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[14]/a[2]")).click();
        //Delete Action Confirm
        driver.findElement(By.xpath("//*[@id='container']/div/form/div/input")).click();

        //Verify Deletion Successful
        VerifyResult(driver, deleteRecord, "Delete Existing Record", "Del");
		
	}
	public static void Filter(WebDriver driver, String text) throws InterruptedException
	{
		 driver.findElement(By.xpath("//*[@id='grid']/div[3]/div/table/thead/tr/th[1]/a[1]/span")).click();
         WebElement Newdriver = driver.findElement(By.xpath("/html/body/div[5]/form/div[1]/input[1]"));
         Thread.sleep(1000);
         Newdriver.clear();
         Newdriver.sendKeys(text);
         driver.findElement(By.xpath("/html/body/div[5]/form/div[1]/div[2]/button[1]")).click();
	
	}
	public static void DropdownSelect(WebDriver driver)
	{
		//Random rnd = new Random();
        //define Double Click Functions
		
		
		 driver.findElement(By.cssSelector("body > div.k-widget.k-window > div.k-popup-edit-form.k-window-content.k-content > div > div:nth-child(6) > span > span > span.k-input")).click();
         //int optionNum = (driver.findElement(By.cssSelector("body > div.k-animation-container.km-popup > div > ul"))).findElements(By.className("k-item")).size;
         //String ButtonLogoCSS = "body > div.k-animation-container.km-popup > div > ul > li:nth-child(" + (rnd.nextInt(optionNum)+1) + ")";
         driver.findElement(By.cssSelector("body > div.k-animation-container.km-popup > div > ul > li:nth-child(5)")).click();
         
       //NextScreen
         driver.findElement(By.cssSelector("body > div.k-widget.k-window > div.k-popup-edit-form.k-window-content.k-content > div > div:nth-child(10) > span > span > span.k-input")).click();
         //string NextScreenXPath = "//*[@id='NEXT_SCREEN_DBID_listbox']/li[" + (rnd.Next(2, optionNum)) + "]";
         driver.findElement(By.xpath("//*[@id='NEXT_SCREEN_DBID_listbox']/li[8]")).click();
         
       //NextState
         driver.findElement(By.cssSelector("body > div.k-widget.k-window > div.k-popup-edit-form.k-window-content.k-content > div > div:nth-child(14) > span > span > span.k-input")).click();
         //string NextStateXPath = "//*[@id='NEXT_STATE_DBID_listbox']/li[" + (rnd.Next(2, optionNum)).ToString() + "]";
         driver.findElement(By.xpath("//*[@id='NEXT_STATE_DBID_listbox']/li[5]")).click();

         //PendingState
         driver.findElement(By.cssSelector("body > div.k-widget.k-window > div.k-popup-edit-form.k-window-content.k-content > div > div:nth-child(16) > span > span > span.k-input")).click();
         //string PendingStateXPath = "//*[@id='PENDING_STATE_DBID_listbox']/li[" + (rnd.Next(2, optionNum)).ToString() + "]";
         driver.findElement(By.xpath("//*[@id='PENDING_STATE_DBID_listbox']/li[5]")).click();

         //EntityType
         driver.findElement(By.cssSelector("body > div.k-widget.k-window > div.k-popup-edit-form.k-window-content.k-content > div > div:nth-child(18) > span > span > span.k-input")).click();
         //int optionNumEntityType = (driver.FindElement(By.CssSelector("#ETT_DBID_listbox"))).FindElements(By.ClassName("k-item")).Count;
         //string EntityTypeCSS = "#ETT_DBID_listbox > li:nth-child(" + (rnd.Next(2, optionNumEntityType)).ToString() + ")";
         driver.findElement(By.cssSelector("#ETT_DBID_listbox > li:nth-child(5)")).click();
         
	}
	public static void VerifyResult(WebDriver driver, List<String> newValue, String message, String AddorDel) throws InterruptedException
	{
		 //Filter
        Filter(driver, newValue.get(0));

        switch  (AddorDel)
        {
            case "Del":
               String expectMessage = "No items to display";
               // Thread.Sleep(500);
                String actualMessagge = driver.findElement(By.cssSelector("#grid > div.k-pager-wrap.k-grid-pager.k-widget > span.k-pager-info.k-label")).getText();
               	System.out.println(actualMessagge);
                if (expectMessage.equals(actualMessagge))
                {System.out.println(message + " function test passed!");}

                else
                {
                    for (int i = 1; i < 2; i++)
                    {
                    	String resultBtnNm = driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr[1]/td[1]")).getText();
                    	
                        if (resultBtnNm.equals(newValue.get(0)))
                        {
                        	System.out.println(message + " function test FAILED!!!!!");
                        	System.out.println("There might be duplicate records, delete them and retry again");
                        }
                        else{ System.out.println(message + " function test pass!!!!!");}
                    }
                }
                break;

            case ("AddUpdate"):
            	String result1 = driver.findElement(By.xpath("//*[@id='grid']/div[4]/table/tbody/tr/td[1]")).getText();
            	
            	
            	
                if (result1.equals(newValue.get(0)))
                { System.out.println(message + " function test passed!"); }
                else{ System.out.println(message + " function test FAILED!"); }
                break;

            default:break;
        }
        driver.navigate().refresh();
    }
}

