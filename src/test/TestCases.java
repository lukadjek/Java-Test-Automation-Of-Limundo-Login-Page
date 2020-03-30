package test;



import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
	
	WebDriver driver;
	
	@Before
	public void setUp() {													// set up chrome configuration before tests start
		
		String userDirectory = System.getProperty("user.dir");	
		
		System.setProperty("webdriver.chrome.driver", userDirectory + "/lib/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	// wait until all hidden elements are present to the driver
		driver.get("https://www.limundo.com/Prijava");
	}
	
	@Test
	public void assertPageUrl() {											// assert page url
			String actual = driver.getCurrentUrl();
			String expected = "https://www.limundo.com/Prijava";
			assertEquals(expected, actual);
	}
	
	@Test
	public void assertPageTitle() {											// assert page title
		String actual = driver.getTitle();
		String expected = "Ulaz - Limundo.com";
		assertEquals(expected, actual);
	}
	
	@Test
	public void assertPageLogo() {											// assert page logo
		boolean logo = driver.findElement(By.className("Limundo_logo")).isDisplayed();
		assertTrue(logo);
	}
	
	
	@Test																	// assert valid login
	public void assertValidLogin() {
		login("validUsername", "validPassword1234");
	}
	
	@Test																	// assert logout after valid login
	public void logoutAfterValidLogin() {
		login("validUsername", "validPassword1234");
		logout();
	}
	
	@Test																	// assert 'password forgotten' link with false email
	public void passwordForgotten_FalseEmail() {
			
		driver.findElement(By.cssSelector("body > div.container > div > div.col-lg-7.col-md-7.col-sm-7.col-xs-12 > div > div.panel-body > form > div.row > div > a")).click();
		
		boolean emailVisible = driver.findElement(By.id("txtEmail")).isDisplayed();
		assertTrue(emailVisible);
		
		WebElement emailText = driver.findElement(By.id("txtEmail"));
		
		emailText.sendKeys("thisIsInvalidMail@bluewin.ch");
		
	}
	
	@Test																	// assert 'password forgotten' link with valid email
	public void passwordForgotten_ValidEmail() {
		
		driver.findElement(By.cssSelector("body > div.container > div > div.col-lg-7.col-md-7.col-sm-7.col-xs-12 > div > div.panel-body > form > div.row > div > a")).click();
		
		boolean emailVisible = driver.findElement(By.id("txtEmail")).isDisplayed();		
		assertTrue(emailVisible);											// assert that email field is visible
		
		WebElement emailText = driver.findElement(By.id("txtEmail"));
		
		String validEmail = validEmail("invalidEmail@bluewin.ch");			// enter your valid email here
		String emptyEmail = new String("   ");
		
		
		assertNotNull("Email cannot be null. You must provide a valid email!", validEmail);		// assert that the email you entered is not null
		assertNotEquals("Email cannot be empty. You must provide a valid email!", emptyEmail.strip(), validEmail.strip());								// assert that the email you entered is not empty
			
		if ( validEmail != null && validEmail.strip().length() != 0 ) {
			emailText.sendKeys(validEmail);														// write that email in the email field if no expections happened above
			WebElement btnSubmit = driver.findElement(By.id("btnPosalji"));												// click the submit button
			btnSubmit.click();
		}
	}
	

	private void login(String yourValidUsername, String yourValidPassword ) {		// insert your valid credentials here
		
		driver.findElement(By.id("txtUsername")).sendKeys(yourValidUsername);
		driver.findElement(By.id("txtPassword")).sendKeys(yourValidPassword);
		
		WebElement btnLogin = driver.findElement(By.id("btnPrijava"));
		btnLogin.click();
	}
	
	private void logout() {
	
		driver.findElement(By.className("popup_NL_close")).click();		// close the annoying popUp
		
		WebDriverWait wait = new WebDriverWait(driver, 20);				// sometimes 'element click intercepted' error might appear
		
		WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#searchnavbar > div.button-navs.navbar-right.col-sm-4.col-md-6 > a.btn.btn-link.out_button.hidden-xs")));
	    	    
		passwordElement.click();
    	
}
	
	private String validEmail(String yourValidEmail) {
		
		return yourValidEmail;
		
	}

	@After
	public void closeDriver() {
		driver.close();
	}
	
	
}
