package domi.testMonPresta;
import java.util.regex.Pattern;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class prestashopTest {
	private WebDriver driver;  
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private HashMap<String, Object> vars;
	private Util monUtil;
	final static Logger logger = Logger.getLogger(domi.testMonPresta.prestashopTest.class);



	@BeforeEach
	public void setUp() throws Exception {

//		String navigateur = System.getenv("navigateur");
//		if (navigateur == "chrome")
//		{
//			System.setProperty("webdriver.firefox.driver", "firefoxdriver");
//			driver = new FirefoxDriver();
//			
//			driver= new EdgeDriver();
//
//		}
//		else
//		{
//			System.setProperty("webdriver.firefox.driver", "firefoxdriver");
//			driver = new FirefoxDriver();
//		}
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--whitelisted-ips=\"\"");
        options.addArguments("window-size=1920,1200");
        driver=new ChromeDriver(options);
//        driver.get("https://opensource-demo.orangehrmlive.com/");
 

		//
		//    FirefoxOptions options = new FirefoxOptions();
		//	FirefoxProfile monProfil = new FirefoxProfile(new File("/Users/dominiquemereaux/Library/Application Support/Firefox/Profiles/zb6qfwx8.monProfil"));
		//	options.setProfile(monProfil);





	}
	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

	@Test 

	public void chercherLeMugF() throws InterruptedException, MalformedURLException {
		driver.quit();
//		FirefoxOptions options = new FirefoxOptions();
//		FirefoxProfile monProfil = new FirefoxProfile(new File("/Users/dominiquemereaux/Library/Application Support/Firefox/Profiles/rfqfjl41.monProfil"));
//		options.setProfile(monProfil);
//		driver = new FirefoxDriver(options);


		driver.get("http://prestashop.qualifiez.fr/en/");
		driver.manage().window().setSize(new Dimension(968, 699));
		driver.manage().window().maximize();
		driver.findElement(By.name("s")).sendKeys("MUG");
		driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
	}
	@Test 
	public void chercherLeMug2() throws InterruptedException, IOException {
		//	FirefoxOptions firefoxOptions = new FirefoxOptions();

		//	WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.79:4444"), firefoxOptions);


		driver.get("http://prestashop.qualifiez.fr/en/");
		driver.manage().window().setSize(new Dimension(968, 699));
		driver.manage().window().maximize();
		File monFichier = driver.findElement(By.name("s")).getScreenshotAs(OutputType.FILE);
	    File toto = new File("toto.png");
	    FileUtils.copyFile(monFichier,toto);

		driver.findElement(By.name("s")).sendKeys("MUG");
		driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		assertEquals(driver.getTitle(),"Search");	 
		assertEquals(driver.findElement(By.name("s")).getAttribute("Value"),"MUG");
		assertEquals(driver.findElement(By.xpath("//*[@id='js-product-list-top']/div[1]/p")).getText(),"There are 5 products.");	    
		List<WebElement> maListe = driver.findElements(By.cssSelector("a.js-search-link"));
		for(WebElement elt : maListe )
		{
			System.out.println(elt.getText());
			System.out.println(elt.isDisplayed());
		}

		driver.findElement(By.cssSelector("#js-product-list-top > div:nth-child(2) > div > div > button")).click();
		List<WebElement> maListe2 = driver.findElements(By.cssSelector("a.js-search-link"));
		for(WebElement elt : maListe2 )
		{
			System.out.println(elt.getText());
			System.out.println(elt.isDisplayed());
		}
		driver.navigate().back();
		Thread.sleep(5000);		

		assertEquals(driver.getTitle(),"monPrestashop");	 

	}
	@Test 
	public void checkerLaBOX() throws InterruptedException {
		driver.get("http://prestashop.qualifiez.fr/en/men/1-1-hummingbird-printed-t-shirt.html#/1-size-s/8-color-white");
		WebElement check1 = driver.findElement(By.xpath("//*[@id='group_2']/li[1]/label/input"));
		monUtil=new Util();
		monUtil.checkLaBox(check1);
		assertTrue (driver.findElement(By.xpath("//*[@id='group_2']/li[1]/label/input")).isSelected());
		monUtil.checkLaBox(check1);
		assertTrue (driver.findElement(By.xpath("//*[@id='group_2']/li[1]/label/input")).isSelected());


	}

	@Test 
	public void testLaFrame() throws InterruptedException, IOException {

		logger.info("Etape 1 : " + "aller sur le site");

		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
		logger.info("Etape 2 : " + "accepter la modale");
		File filePhoto1=driver.findElement(By.id("accept-choices")).getScreenshotAs(OutputType.FILE);	
		File destFile1 = new File("./Ma Modale.png");
		Files.copy(filePhoto1, destFile1);


		driver.findElement(By.id("accept-choices")).click();
		logger.info("Etape 3 : " + "aller sur la frame");
		logger.warn("This is warn : " );

		TakesScreenshot  srcPhoto = ((TakesScreenshot)driver);
		File filePhoto = srcPhoto.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./toto.png");
		Files.copy(filePhoto, destFile);


		WebDriver maFrame = driver.switchTo().frame("iframeResult");
		srcPhoto = ((TakesScreenshot)maFrame);
		filePhoto = srcPhoto.getScreenshotAs(OutputType.FILE);
		destFile = new File("./titi.png");
		Files.copy(filePhoto, destFile);

		//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > button")));
		filePhoto=button.getScreenshotAs(OutputType.FILE);
		destFile = new File("./tata.png");
		Files.copy(filePhoto, destFile);
		button.click();
		Alert toto = driver.switchTo().alert();
		assertEquals("Hello! I am an alert box!",toto.getText());
		toto.accept();
		driver.switchTo().defaultContent();

	}

	//@Test 
	public void chercherLeMugAvecBouton() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("sessionName", "Automation test session on android web");
		capabilities.setCapability("sessionDescription", "This is example android web testing"); 
		capabilities.setCapability("deviceOrientation", "portrait"); 
		capabilities.setCapability("platformName", "android"); 
		capabilities.setCapability("browserName", "chrome"); 
		capabilities.setCapability("deviceName", "toto");
		capabilities.setCapability("platformVersion", "10");

		// Création d'un remote driver pour se connecter au serveur APPIUM
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.get("http://prestashop.qualifiez.fr/en/");

		driver.findElement(By.name("s")).sendKeys("MUG");
		driver.findElement(By.cssSelector("#search_widget > form > button > i")).click();
		assertEquals(driver.getTitle(),"Search");	   
	}

	@Test
	public void compterLesFenetres() {

		driver.get("http://www.qualifiez.fr/examples/Selenium/project-list.php");
		driver.manage().window().setSize(new Dimension(968, 699));
		driver.manage().window().maximize();
		// recuperation des handles de fenetres
		Set<String> mesFenetres = driver.getWindowHandles();
		String F1="";
		for (String elt : mesFenetres)
			F1 = elt;
		driver.findElement(By.id("btnNewWindow")).click();
		// recuperation des handles de fenetres
		Set<String> mesFenetres2 = driver.getWindowHandles();
		for (String elt : mesFenetres2)
			System.out.println(elt);
		// je vais sur la fenêtre en utilisant le nom dans le code source
		driver.switchTo().window("toto");
		assertEquals(driver.findElement(By.cssSelector("p")).getText(), "New Window ...");  
		// je vais sur la fenetre en utilisant le Handle
		driver.switchTo().window(F1);   
		assertEquals(driver.getTitle(), "Projets");

	}

	@Test
	public void testActions() throws Exception {
		driver.get("http://prestashop.qualifiez.fr/en/");
		driver.manage().window().maximize();
		Actions builder = new Actions(driver);
		Action mouseOverClothes = builder.moveToElement(driver.findElement(By.cssSelector("#category-3 > a:nth-child(1)"))).build();
		mouseOverClothes.perform();

		driver.findElement(By.partialLinkText("WOMEN")).click();


	} 
	@Test
	// Faire une copie ecran.
	public void testPrestashopCopie() throws Exception {
		driver.manage().window().setSize(new Dimension(1050, 840));
		driver.get("http://prestashop.qualifiez.fr/en/");
		// creation objet pour faire des copies ecran
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		// copie ecran, resultat dans un objet de type FILE
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		// creation d'un fichier sur C:
		File DestFile=new File("toto.png");
		//Copie de la copie ecran dans dans le fichier 
		Files.copy(SrcFile, DestFile);
	}

	@Test
	// exemple javascript
	public void testPrestashop5() throws Exception {
		// Aller sur la page prestashop
		driver.get("http://prestashop.qualifiez.fr/en/");
		// saisir le champ de recherche
		driver.findElement(By.name("s")).sendKeys("MUG");
		// cliquer sur le bouton de recherche
		driver.findElement(By.cssSelector("button > .search")).click();  
		// attendre et contrôler le résultat
		Thread.sleep(5000);
		assertEquals(driver.findElement(By.cssSelector(".total-products > p")).getText(),"There are 5 products.");
	}
	@Test
	// exemple javascript
	public void testPrestashopAttente() throws Exception {
		// Aller sur la page prestashop
		driver.get("http://prestashop.qualifiez.fr/en/");
		// saisir le champ de recherche
		driver.findElement(By.name("s")).sendKeys("MUG");
		// cliquer sur le bouton de recherche
		driver.findElement(By.cssSelector("button > .search")).click();  
		// attendre et contrôler le résultat
		//	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".total-products > p"))));
		assertEquals(driver.findElement(By.cssSelector(".total-products > p")).getText(),"There are 5 products.");
	}

	@Test
	// exemple javascript
	public void testPrestashop6() throws Exception {

		// Aller sur la page prestashop
		driver.get("http://prestashop.qualifiez.fr/en/");	  
		// saisir le champ de recherche
		driver.findElement(By.name("s")).sendKeys("Mug");
		// cliquer sur le bouton de recherche
		driver.findElement(By.cssSelector("button > .search")).click();  
		// contrôler le résultat
		assertEquals(driver.findElement(By.cssSelector(".total-products > p")).getText(),"There are 5 products.");

	}

	@Test
	// exemple javascript
	public void testPrestashopCurrency() throws Exception {

		// Aller sur la page prestashop
		driver.get("http://prestashop.qualifiez.fr/en/");

		// recuperer les liens a vers des devises
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='_desktop_currency_selector']/div/ul//a"));
		for (WebElement elt : list)
		{
			System.out.println("list:");
			System.out.println(elt.getAttribute("Title"));
			System.out.println(elt.getAttribute("outerHTML"));
		}

	}

	@Test
	// exemple javascript
	public void testJavascript() throws Exception {
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get("http://prestashop.qualifiez.fr/en/");
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		// saisie de MUG
		js1.executeScript("document.getElementsByName('s')[0].value='MUG';");
		// click sur bouton

		js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button > .search")));
		//	  driver.findElement(By.cssSelector("button > .search")).click();  
		assertEquals(driver.findElement(By.cssSelector(".total-products > p")).getText(),"There are 5 products.");
	}

	@Test
	//fenetre
	public void testFenetre() throws Exception {
		driver.get("http://www.qualifiez.fr/examples/Selenium/project-list.php");
		//compter les fenetre
		assertEquals(driver.getWindowHandles().size(),1);
		driver.findElement(By.id("btnNewWindow")).click();
		Set<String> whNow = driver.getWindowHandles();
		// compter les fenêtres 
		assertEquals(driver.getWindowHandles().size(),2);
		// aller sur la deuxième fenêtre
		driver.switchTo().window("toto");
		assertEquals(driver.findElement(By.cssSelector("p")).getText(), "New Window ...");
	}


	@Test
	// Traitement des alertes
	public void testPrestashop4() throws Exception {
		driver.get("http://www.qualifiez.fr/examples/Selenium/project-list.php");
		driver.findElement(By.id("btnAlert")).click();
		assertEquals(driver.switchTo().alert().getText(), "Info");
		Alert monPopup = driver.switchTo().alert();
		monPopup.accept();
	}
	@Test
	public void testPrestashopAction() throws Exception {

		Util monUtil = new Util();

		// demarage du test
		driver.get("http://prestashop.qualifiez.fr/en/");

		assertTrue(monUtil.lienVisible(driver,"//*[@id='category-3']/a","//*[@id='category-4']/a"));
		assertTrue(monUtil.lienVisible(driver,"//*[@id='category-3']/a","//*[@id='category-5']/a"));
		assertTrue(monUtil.lienVisible(driver,"//*[@id='category-6']/a","//*[@id='category-7']/a"));

	}
	@Test
	public void testPrestashopAPK() throws Exception {

		// Definition du mobile sur lequel on va exécuter les tests
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("sessionName", "Automation test session on android web");
		capabilities.setCapability("sessionDescription", "This is example android web testing"); 
		capabilities.setCapability("deviceOrientation", "portrait"); 
		capabilities.setCapability("platformName", "android"); 
		capabilities.setCapability("app", "/Users/dominiquemereaux/AndroidStudioProjects/Dice/app/build/outputs/apk/app-debug.apk"); 
		capabilities.setCapability("deviceName", "toto");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("automationName", "appium");
		capabilities.setCapability("appPackage", "com.example.dominiquemereaux.dice");	  
		// Création d'un remote driver pour se connecter au serveur APPIUM
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);





	}
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {


				alert.accept();

			} else {

				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
