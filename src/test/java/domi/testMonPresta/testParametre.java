package domi.testMonPresta;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

//@RunWith(Parameterized.class)
public class testParametre{
	
  private WebDriver driver;
 
  // Ces variables privees sont mises a jour par le constructeur
//  private String mot;
 // private String resultat;
 
// les jeux de donnees sont crees a partir d un fichier XML 
/*	@Parameters public static Collection<Object[]> val() throws ParserConfigurationException, SAXException, IOException{
		List<Object[]> TestData = new ArrayList<Object[]>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("./Donnees.xml"));
		final NodeList jeux = document.getElementsByTagName("jeux");	
		for(int j = 0; j<jeux.getLength(); j++) {
		    final Element jeu = (Element) jeux.item(j);
		    TestData.add(new Object[]{ jeu.getAttribute("mot"), jeu.getAttribute("message")} );
		}		
		return TestData;
	}
// les variables doivent respectees l'ordre dans la collection
  public testParametre(String motLocal, String resultatLocal)
  {
	  this.mot=motLocal;
	  this.resultat=resultatLocal;
  }*/

  @BeforeEach
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "geckodriver");
    driver = new FirefoxDriver();
    driver.get("http://prestashop.qualifiez.fr/en/");
  }
  
  @ParameterizedTest
  @CsvFileSource(resources = "donnees.csv", numLinesToSkip = 1)
  public void testPrestashop(String input, String expected) throws Exception {
	    driver.findElement(By.name("s")).sendKeys(input);
	    driver.findElement(By.cssSelector("#search_widget > form > button > i")).click();
	    assertEquals(driver.findElement(By.xpath("//*[@id='js-product-list-top']/div[1]/p")).getText(),expected);	    
  } 

  
  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
  }
}
