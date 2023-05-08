package domi.testMonPresta;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import domi.testMonPresta.pageRecherche;

public class pageAccueil {
	
	private WebDriver driverAccueil;

	@FindBy(how = How.NAME, using = "s")
    private WebElement champRecherche;
	@FindBy(how = How.CSS, using = "button > .search")
    private WebElement boutonRecherche;
	
	public pageAccueil(WebDriver driver){
		driverAccueil = driver;
	    if (!"monPrestashop".contentEquals(driverAccueil.getTitle()))
	    { throw new IllegalStateException ("pas la page pour se connecter" +driverAccueil.getTitle() ); 	}
	    

	}
	
	public pageRecherche rechercher(String mot)
	{
		   champRecherche.click();
		   champRecherche.clear();
		   champRecherche.sendKeys(mot);
		   boutonRecherche.click();
		   return PageFactory.initElements(driverAccueil, pageRecherche.class);
	}


}

	


