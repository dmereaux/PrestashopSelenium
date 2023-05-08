package domi.testMonPresta;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Util {
	
	void checkLaBox(WebElement elt)
	{
		if (!elt.isSelected())
			elt.click();
	}
	void deCheckLaBox(WebElement elt)
	{
		if (elt.isSelected())
			elt.click();
	}
	public Boolean lienVisible(WebDriver driver, String chemin1, String chemin2) {
		// TODO Auto-generated method stub
		  WebElement element = driver.findElement( By.xpath(chemin1));
//		  assertFalse(driver.findElement(By.xpath(chemin2)).isDisplayed());
		  
		  Actions action = new Actions(driver);
		  action.moveToElement(element);
		
		  action.perform();
		  return driver.findElement(By.xpath(chemin2)).isDisplayed();

	}


}
