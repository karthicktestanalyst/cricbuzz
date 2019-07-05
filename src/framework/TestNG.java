package framework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import moduleFiles.LiveScore;
import excel.Excel;

public class TestNG {

static Excel EXLObj;
static WebDriver driver;

@BeforeSuite
public void start()
{
	//Connect and opening application URL
	System.setProperty("webdriver.chrome.driver","F:\\Karthick\\Driver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.cricbuzz.com/");
	//Live Score page
	driver.findElement(By.xpath("//a[@class='cb-hm-mnu-itm'][@title='Live Cricket Score']")).click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
	EXLObj = new Excel();
}

//@Test (priority=0)//squads
//public void Squad() throws IOException
//{
//CFSeries cfs= new CFSeries();
//cfs.squad(driver,EXLObj);
//}

@Test(priority = 0)//CurrentMatches
public void liveMatch() throws IOException
{
	LiveScore LVSObj=new LiveScore();
	LVSObj.liveMatch(driver, EXLObj);
}

@AfterTest//to close browser
public void afterTest()
{
	driver.close();
}

}
