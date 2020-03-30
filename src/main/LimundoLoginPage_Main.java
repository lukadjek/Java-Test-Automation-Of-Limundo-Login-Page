package main;

/**
 * If needed, do the necessary import of libraries which are all located in this project!
 */

// do not import the whole package, but rather only what's really used in the code
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class LimundoLoginPage_Main {

	private static String browser;				// static browser instance to be used in several methods
	private static WebDriver driver;			// static driver instance to be used in several methods


	public static void main(String[] args) {	
		runTheTest();									// use as little code in the main method as possible

	}
	
	public static Object runTheTest() {
			
		setBrowserName("Use Safari please!");			// set the right browser to be used
		setBrowserConfiguration();						// set the browser configuration
	
		if ( openLoginPage() == true ) {				// if login page is found, go further with:
			insertFalseLoginDetails();					// inserting false data for login,
			closeDriver();								// closing the driver itself
		}
		else
			System.err.println( "\n Problems with opening login page occured! Please check in detail!" );	// inform me if something's wrong and do not deal with the driver	
		return browser;
		
	}


	public static void setBrowserConfiguration() {
		
		String userDirectory = System.getProperty("user.dir");				// locate user directory and connect it below with the right driver		
		
			// check which browser is in use
		if ( getBrowser().contains("Firefox") ) {
			System.setProperty("webdriver.gecko.driver", userDirectory + "/lib/geckodriver");
			driver = new FirefoxDriver();
		}
	
		else if ( getBrowser().contains("Safari") ) {
			driver = new SafariDriver();
		}
	
		else if ( getBrowser().contains("Chrome") ) {
			System.setProperty("webdriver.chrome.driver", userDirectory + "/lib/chromedriver");
			driver = new ChromeDriver();
		}
		
	}


	public static boolean openLoginPage() {		

		String URL = "https://www.limundo.com/Prijava";
		
			// do slight appearance modifications
		driver.manage().window().setSize(new Dimension(800, 600));	
		driver.manage().window().setPosition(new Point(300, 100));
		driver.get(URL);
		 
		String title = driver.getTitle();												// get title
		
		boolean chechTitleName = title.equalsIgnoreCase("ulaz - limundo.com");			// see if the current title matches the expected title
		
		if ( chechTitleName ) {															// print message 
			System.out.println( "\t Title matches." );
		}
		else {
			System.out.println( "\t Title does not match. Please check the title name." );
		}
		
		return chechTitleName ? true : false;											// return smooth and readable lambda boolean value
		
	}

	public static void insertFalseLoginDetails() {
		
			// create strings that we will use further
		String userName = new String("txtUsername");
		String eMail = new String("txtPassword");

		WebElement username = driver.findElement(By.id(userName));
		WebElement email = driver.findElement(By.id(eMail));
		WebElement btnLogin = driver.findElement(By.id("btnPrijava"));

			// do some actions on those strings
		username.sendKeys("myUsername");
		email.sendKeys("myEmail");
		btnLogin.click();
		
			// check if we are on the right page
		if (userName.equalsIgnoreCase("txtusername") ) {
			System.out.println( "\t Username matches." );
		}
		if ( eMail.equalsIgnoreCase("txtpassword") ) {
			System.out.println( "\t Email matches." );
		}
		
	}
	
	public static void closeDriver() {
		
			// close driver 5 seconds after all tests have been successfully conducted 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.close();
		
		System.out.println( "\t Driver closed after 5 seconds." );
		
	}
	
	public static String setBrowserName(String browserToBeUsed) {
		
		// This code will work with: Safari, Chrome or Firefox. 
		
		if ( browserToBeUsed != null ) {							// if browserToBeUsed is set to null, use default browser for ex. "Chrome"
		
		if ( browserToBeUsed.contains("Chrome") )					// check the whole browserToBeUsed string in order to determine the right browser to be used
			return setBrowser("Chrome");								 
					
		else if ( browserToBeUsed.contains("Safari") )	
			return setBrowser("Safari");
		
		else if ( browserToBeUsed.contains("Firefox") ) {
			return setBrowser("Firefox");								
		}
		
		else 
			return setBrowser("Chrome");								// if browserToBeUsed is not null, but is set to some other value, use default browser for ex. "Chrome"
		
		} else
			return setBrowser("Chrome");
	}


	public static String getBrowser() {
		return browser;
	}


	public static String setBrowser(String browser) {
		LimundoLoginPage_Main.browser = browser;
		return browser;
	}


	

	
	
	
	

}
