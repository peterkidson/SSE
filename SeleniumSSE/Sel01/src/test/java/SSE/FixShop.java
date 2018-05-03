package SSE;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

/**
 * Barebones SSE price check test
 *
 * @author Peter Kidson  May 2018
 */


/**
 * This is hard-coded for one particular case.
 * The way forward would be to have a text or xls file with rows holding
 * information replacing some of the hard-coded data
 * eg
 * - tariffs :  Energy, Prices and tariff information, {a postcode}, Currys,
 *              Direct Debit and paperless bills, {a tariff}
 *
 */

public class FixShop {

	@Test
	public void gotoPage() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");  // Chrome v 66.0   chromedriver.exe v 2.38
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://sse.co.uk/home");
		Thread.sleep(2000);
		assertTrue(driver.getTitle().equals("Energy Supplier, Phone & Broadband, Boiler Cover - SSE"));

		String xpEnergy = ".//body/descendant::div[@class='innerNavList']/descendant::a[@title='Energy']";
		WebElement weEmergy = driver.findElement(By.xpath(xpEnergy));
		weEmergy.click();

		String xpPricesTariffs = ".//body/descendant::div[@class='navHeading1']/following::ul/descendant::li" +
				"/descendant::a[contains(text(),'Prices and tariff information')]";
		WebElement wePricesTariffs = driver.findElement(By.xpath(xpPricesTariffs));
		Thread.sleep(2000);
		wePricesTariffs.click();

		String xpInnerContainer = ".//body/descendant::div[@class='innerContainer configuration']";
		WebElement weInnerContainer = driver.findElement(By.xpath(xpInnerContainer));

		String xpPostcode = "//descendant::input[@type='text' and @id='postcode']";
		WebElement wePostcode = driver.findElement(By.xpath(xpPostcode));
		Thread.sleep(2000);
		wePostcode.sendKeys("PO9 1QH");

		weInnerContainer = driver.findElement(By.xpath(xpInnerContainer));
		String xpViewTariffsButton = "//descendant::input[@type='button']";
		WebElement weViewTariffsButton = driver.findElement(By.xpath(xpViewTariffsButton));
		Thread.sleep(2000);
		weViewTariffsButton.click();
		Thread.sleep(5000);

		String xpTariff = ".//body/descendant::div[@class='outerContainer']" +
				"/descendant::div[@class='innerContainer search tariffPrices']" +
				"/descendant::h3[@class='best-value' and contains(text(),'(Currys PC World)')]" +
				"/following::table/descendant::th[contains(text(), 'Direct Debit and paperless bills')]" +
				"/following::td[2]/descendant::span[@class='inclVat']";
		WebElement weTariff = driver.findElement(By.xpath(xpTariff));
		assertTrue(weTariff.getText().equals("14.80"));


		Thread.sleep(3000);
		driver.quit();
	}

}

