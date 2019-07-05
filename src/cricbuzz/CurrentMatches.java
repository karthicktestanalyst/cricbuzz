package cricbuzz;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CurrentMatches {

	//"scorecard" is an instance for "Scorecard" 
	@FindBy(how=How.XPATH, using="//*[@id=\"page-wrapper\"]/div[4]/div[1]/nav/a[2]")
	private WebElement scorecard;
	
	// "driver" is private instance for WebDriver Class
	private WebDriver driver = null;
				
	//"builder" is private instance for Actions Class
	private Actions builder = null;
	
	//Current Innings Variable
	String innings = null;
	
	/*
	 * The constructor  "Current Matches" does initialize the webdriver and
	 * action class objects, initialize the PageFactory for this
	 * web page
	 * 
	 * @param driver - Receives webdriver object
	 */
	public CurrentMatches(WebDriver driver, String curMatch) {
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
		//builder.moveToElement(driver.findElement(By.xpath("//h3/a[@title='"+curMatch+"']"))).click().build().perform();
		builder.moveToElement(scorecard).click().build().perform();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		innings = driver.findElement(By.xpath("//div[@class='cb-col cb-col-67 cb-scrd-lft-col html-refresh ng-isolate-scope']/div[2]")).getAttribute("id");
	}
	
	/*
	 * To get and send squads details
	 * 
	 * @param i - To choose the options

	 * 
	 * @return <Any> - Returns value based on value it processed
	 */
	
	public String toSendDetails(char i)
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//h1")).getText();
			case 'b': return driver.findElement(By.xpath("//div[@class='cb-col cb-col-67 cb-scrd-lft-col html-refresh ng-isolate-scope']/div[1]")).getText();
			case 'c': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div/span[1]")).getText();
			case 'd': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div/span[2]")).getText();
			case 'e': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']")).getText();
			case 'f': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-8 text-bold text-black text-right']")).getText();
			case 'g': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[text()='Extras']/following-sibling::div[@class='cb-col-32 cb-col']")).getText();
			case 'h': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[text()='Total']/following-sibling::div[@class='cb-col-32 cb-col']")).getText();
			case 'i': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-27']")).getText();
			case 'j': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div[@class='cb-col cb-col-100 cb-col-rt cb-font-13']")).getText();
			case 'k': return driver.findElement(By.xpath("//*[@id=\"innings_1\"]/div[5]/div[1]/div[1]")).getText();
			case 'l': return driver.findElement(By.xpath("//*[@id=\"innings_1\"]/div[5]/div[1]/div[2]")).getText();
			case 'm': return driver.findElement(By.xpath("//*[@id=\"innings_1\"]/div[5]/div[1]/div[3]")).getText();
			case 'n': return driver.findElement(By.xpath("//*[@id=\"innings_1\"]/div[5]/div[2]/div[1]")).getText();
			case 'o': return driver.findElement(By.xpath("//*[@id=\"innings_1\"]/div[5]/div[2]/div[2]")).getText();
			case 'p': return driver.findElement(By.xpath("//*[@id=\"innings_1\"]/div[5]/div[2]/div[3]")).getText();
		}
		return null;
	}
	
	@SuppressWarnings({"rawtypes"})
	public List toSendList(char i) {
		switch(i) {
			case 'a': return driver.findElements(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
			+ "/div[@class='cb-col cb-col-27 text-bold']/a"));
			case 'b':  return driver.findElements(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms ']"
			+ "/div[@class='cb-col cb-col-40']/a"));
			case 'c': return driver.findElements(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-27 ']/a"));
		}
		return null;
	}
	/*
	 * To get and send current match details
	 * 
	 * @param i - To choose the options
	 * @param j - Index for WebElement List
	 * 
	 * @return String - Returns value
	 */
	public String detailsSending(char i, int j) {
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//div[@class='cb-nav-subhdr cb-font-12']/a["+(j+1)+"]")).getAttribute("title");
			case 'b': return driver.findElement(By.xpath("//div[@class='cb-nav-subhdr cb-font-12']/a["+(j+1)+"]")).getAttribute("title");
			case 'c': return driver.findElement(By.xpath("//div[@class='cb-nav-subhdr cb-font-12']/span[4]/span["+(j+1)+"]")).getText();
			case 'd': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col-73 cb-col']/a["+j+"]")).getText()+",";
		}
		
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 * @param  i - Index for choosing
	 * @param search - Player's Name
	 */
	public String sendDetailsByString( char i,String search) {
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
					+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 ']/a[contains((text()),'"
					+search+"')]/parent::div/following-sibling::div[1]/span")).getText();
			case 'b': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
					+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 text-bold']/a[contains((text()),'"
					+search+"')]/parent::div/following-sibling::div[1]/span")).getText();
		}
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 *  @param  i - Index for choosing
	 *  @param search - Webelement for player
	 */
	public String toSendDetails( char i,WebElement search) {
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//div[@id='"+innings+"']"
					+ "/div/div[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 ']"
					+ "/a[@title='"+search.getAttribute("title")+"']")).getText();
			case 'b': return search.getAttribute("href");
			case 'c': return driver.findElement(By.xpath("//div[@id='"+innings+"']"
					+ "/div/div[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 text-bold']"
					+ "/a[@title='"+search.getAttribute("title")+"']")).getText();
			case 'd': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms ']/div[@class='cb-col cb-col-40']"
					+ "/a[@title='"+search.getAttribute("title")+"']")).getText();
		}
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 *  @param  i - Index for choosing
	 *  @param search - Player's Name
	 *  @param j - Index for WebElement List
	 */
	public String toSendDetails( char i,String search, int j) {
		switch(i) {
		case 'a': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
				+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 ']"
				+ "/a[contains((text()),'"+search+"')]/parent::div/following-sibling::div["+j+"]")).getText();
		case 'b': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
				+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 text-bold']"
				+ "/a[contains((text()),'"+search+"')]/parent::div/following-sibling::div["+j+"]")).getText();
		case 'c': return driver.findElement(By.xpath(" //div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms ']"
				+ "/div[@class='cb-col cb-col-40']/a[contains(text(),'"+search+"')]/parent::div/following-sibling::div["+j+"]")).getText();
		}
		return null;
	}
}