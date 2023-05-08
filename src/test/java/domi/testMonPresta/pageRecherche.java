package domi.testMonPresta;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageRecherche {

	private WebDriver driverRecherche;
	@FindBy(how = How.CSS, using = ".total-products > p")
    private WebElement resRecherche;
	@FindBy(how = How.XPATH, using = "//*[@id=\'content']/h4")
	private WebElement rienTrouve;
	@FindBy(how = How.XPATH, using = "//*[@id='js-product-list-top']/div[2]/div/div/button")
	private WebElement listeTri;	
	@FindBy(how = How.XPATH, using = "//*[@id='js-product-list-top']/div[2]/div/div/div/a")
	private List<WebElement> items;


	public pageRecherche(WebDriver driverAccueil) {
		driverRecherche = driverAccueil;
	    if ("presta-recherche".contentEquals(driverRecherche.getTitle()))
	    { throw new IllegalStateException ("pas la page pour se connecter" +driverAccueil.getTitle() ); 	}
	}
   public String nbElementTrouve()
   {
	   return resRecherche.getText();
   }
   public String PasDeResultat()
   
   {
	   return rienTrouve.getText();
   }
   public pageRecherche trier(String item)
   {
	   listeTri.click();
	   for (WebElement elt :items )
	   {
		   if (elt.getText().contains(item))
			   elt.click();
	   }
	   return this;
   }


}
