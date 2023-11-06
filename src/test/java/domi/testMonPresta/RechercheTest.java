package domi.testMonPresta;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.time.Duration;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;


public class RechercheTest {
	  private static WebDriver driver;
	  private static String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private pageAccueil accueil;




	  @BeforeEach
	 public void setUp() throws Exception {
		  
		// creation du driver
			System.setProperty("webdriver.gecko.driver", "geckodriver");
		    driver = new FirefoxDriver();
		    baseUrl = " http://prestashop.qualifiez.fr";


//		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(baseUrl);

		accueil =  PageFactory.initElements(driver, pageAccueil.class);

		  
	};

	// Create a test using SignInPage and PageProject to check successful login  
	@Test
	public void testRecherche() throws Exception {
		pageRecherche res = accueil.rechercher("MUG");
		res.trier("Name, A to Z");
		Thread.sleep(2000);
		assertEquals("There are 5 products.",res.nbElementTrouve());
	}
	@Test
	public void testRechercheInfructueuse() throws Exception {
		pageRecherche res = accueil.rechercher("dytfgouyh");
		assertEquals("Sorry for the inconvenience.",res.PasDeResultat());
	}

	  @AfterEach
	 public  void tearDown() throws Exception {
	    driver.quit();
	  }




	}



