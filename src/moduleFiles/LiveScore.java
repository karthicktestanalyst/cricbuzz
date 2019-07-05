package moduleFiles;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cricbuzz.CurrentMatches;
import excel.Excel;

public class LiveScore
{
	public void liveMatch(WebDriver driver,Excel ELObj) throws IOException
	{
		//Live Teams selected
		
		//driver.findElement(By.xpath("(//*[@class=\"text-hvr-underline text-bold\"])[1]")).click();
		driver.findElement(By.linkText("Pakistan vs Bangladesh,")).click();
		//driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[5]/div[1]/div[1]/div/div[1]/h3/a")).click();
		//driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[5]/div[1]/div[2]/div/div[1]/h3/a")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Score board page
		
		driver.findElement(By.xpath("//*[@id=\"matchCenter\"]/div[2]/nav/a[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
	    LiveScore ls = new LiveScore();
	    System.out.println("Current Url: " + driver.getCurrentUrl());
	    ls.liveUpdates1(driver, ELObj, driver.getCurrentUrl(), "Live Cricket Score, Commentary");
	 }

	 @SuppressWarnings("unchecked")
	 public void liveUpdates1(WebDriver driver, Excel ELObj, String curURL, String cMatch) throws IOException {
	 	int rwNum = 0;
	 	System.out.println("Current Match URL: " +curURL);
	 	driver.get(curURL);
	 	
	 	//Current Match Scorecard Table is fetched
	 	CurrentMatches CMObj = new CurrentMatches(driver, cMatch);
	 	
	 	//Create Sheet and Rows in Excel
	 	ELObj.toShtCrte("Live_Score");
	 	ELObj.rowCreation(rwNum++);
	 	ELObj.cellFormatting("tijga");
	 	ELObj.cellCreationAndWrite(0, CMObj.toSendDetails('a').toString());
	 	ELObj.cellFormatting("rs");
	 	
	 	String[] frTitle = {"Series","Venue","Date and Time: "};
	 	ELObj.rowCreation(rwNum++);
	 	
	 	for(int i = 0; i < 1; i++) {
 		String data = frTitle[i]+": "+CMObj.detailsSending('a', i);
 		ELObj.cellFormatting("tiju4");
 		ELObj.cellCreationAndWrite(0, data);
 		ELObj.cellFormatting("rsx");
 		}
	 	
	 	for(int i = 1; i <= 1; i++) {
	 		String data = frTitle[i]+": "+CMObj.detailsSending('b', i);
	 		
	 		ELObj.cellCreationAndWrite(4, data);
	 	}
	 	
	 	for(int i =0;i<3;i++)
	 		frTitle[2] += CMObj.detailsSending('c', i);
	 	ELObj.cellCreationAndWrite(10,frTitle[2]);
	 	
	 	ELObj.rowCreation(rwNum++);
	 	ELObj.cellFormatting("t");
	 	String status = CMObj.toSendDetails('b').toString();
	 	System.out.println(status);
	 	ELObj.cellCreationAndWrite(0, status);
	 	ELObj.cellFormatting("hgoqrs");
	 	ELObj.rowCreation(rwNum++);
	 	ELObj.cellFormatting("t");
	 	for(int j=0;j<7;j++) {
	 		if(j==0) 
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('c').toString());
	 		else if(j==6)
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('d').toString());
	 		else
	 			ELObj.cellCreationAndWrite(j,"");
	 		if(j==0||j==6) {
				ELObj.cellFormatting("vhrgnq");
			}
	 		ELObj.cellFormatting("kdps");
	 	}
	 	
	 	List<WebElement> BtmnNme = null;
	 	try {
	 		BtmnNme =  CMObj.toSendList('c');
	 		BtmnNme.addAll(CMObj.toSendList('a'));
	 	}
	 	catch(NoSuchElementException e){}
	 	char flag1 = 'y';
	 	String ColNme[] = {"Batsman","","R","B","4s","6s","SR","Extra","Total","Fall of Wickets","Bowler","O","M","R","W","NB","WD","ECO"};
	 	String plyrName = null;
	 	ELObj.cellFormatting("t");
	 	for(int i= 0;i<BtmnNme.size()+1;i++) {
	 		ELObj.rowCreation(rwNum++);
	 		char flag2 = 'y';
	 		if(flag1!='y') {
	 			try {
	 				plyrName = CMObj.toSendDetails('a',BtmnNme.get(i-1));
	 			}
	 			catch(NoSuchElementException e){	
	 				plyrName = CMObj.toSendDetails('c',BtmnNme.get(i-1));
	 			}
	 		}
	 		for(int j =0; j<7; j++) {
	 			if(j==0) 
	 				ELObj.cellFormatting("c");
				else 
					ELObj.cellFormatting("b");
				if(j==2) 
					ELObj.cellFormatting("j");
				else 
					ELObj.cellFormatting("u");
				
	 			if(flag1=='y') {
	 				ELObj.cellFormatting("ig");
					if(j!=1) {
						ELObj.cellFormatting("mqr");
					}
					if(j==0) 
						ELObj.cellFormatting("j");
					
	 				ELObj.cellCreationAndWrite(j,ColNme[j]);
	 				ELObj.cellFormatting("ldps");
	 			}
	 			else
	 			{
	 				ELObj.cellFormatting("hf");
	 				if(flag2=='y') {
	 					if(j==0) {
	 						ELObj.cellFormatting("igoqx");			
	 						
	 						ELObj.cellCreationAndWrite(j++,plyrName);
	 						ELObj.cellFormatting("rswt");
	 					}
	 					if(j==1) {
	 						ELObj.cellFormatting("hf");	
	 						try {
	 							ELObj.cellCreationAndWrite(j,CMObj.sendDetailsByString('a',plyrName));
	 						}
	 						catch(NoSuchElementException e){	
	 							ELObj.cellCreationAndWrite(j,CMObj.sendDetailsByString('b',plyrName));
	 						}
	 					}
	 						
	 					flag2 = 'n';
	 				}
	 				else {
	 					try {
	 						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('a',plyrName,j));
	 					}
	 					catch(NoSuchElementException e){	
	 						ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('b',plyrName,j));
	 					}
	 				}
	 				ELObj.cellFormatting("rs");
	 			}
	 			if(j==6) flag1='n';
	 			ELObj.cellFormatting("wt");
	 		}
	 	}
	 	//Creating "Extra", "Total", "Yet to Bat" and "Fall of Wickets"
	 	int p = 7;
	 	for(int i =0;i<3;i++) {
	 		ELObj.rowCreation(rwNum++);
	 		for(int j=0;j<7;j++) {
	 			if(j==0&&(i==0||i==1)) {
	 				ELObj.cellFormatting("hf");
	 				ELObj.cellCreationAndWrite(j,ColNme[p]);
	 				ELObj.cellFormatting("rswt");
	 				if(i==1) flag1 = 'n';
	 			}
	 			else if(j==2&&(i==0||i==1)) {
	 				if(p==7) {
	 					ELObj.cellFormatting("hfjb");
	 					ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('e').toString());
	 					ELObj.cellFormatting("rswt");
	 				}
	 				else {
	 					ELObj.cellFormatting("hfjb");
	 					ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('f').toString());
	 					ELObj.cellFormatting("rswt");
	 				}
	 			}
	 			else if(j==3&&(i==0||i==1)) {
	 				if(p==7) {
	 					
	 					ELObj.cellFormatting("hfuc");
	 					ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('g').toString());
	 					ELObj.cellFormatting("1ryswt");
	 					p++;
	 				}
	 				else {
	 					
	 					ELObj.cellFormatting("hfc");
	 					ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('h').toString());
	 					ELObj.cellFormatting("1ryswt");
	 				}
	 			}
	 			else if((j==0||j==1)&&i==2) {
	 				try {
	 					if(j==0) {
	 						ELObj.cellFormatting("hf");
	 						try {
	 							ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('i').toString());
	 							ELObj.cellFormatting("rswt");
	 						}
	 						catch(NoSuchElementException e){
	 							rwNum--;
	 						}
	 						catch(NullPointerException e) {}
	 					}
	 					else {
	 						ELObj.cellFormatting("cfhoq");
	 						plyrName = "";
	 						for(int k=1;;k++) {
	 							try {
	 							plyrName += CMObj.detailsSending('d',k);
	 							}
	 							catch(NoSuchElementException e){
	 								break;
	 							}
	 						}
	 						ELObj.cellCreationAndWrite(j,plyrName);
	 						ELObj.cellFormatting("12ryswt");
	 					}
	 				}
	 				catch(NoSuchElementException e){}
	 			}
	 			else {
	 				ELObj.cellCreationAndWrite(j,"");
	 				ELObj.cellFormatting("wst");
	 			}
	 		}
	 	}
	 	
	 	//Creating "Bowler" column header in Excel
	 	for(int i = 0;i<2;i++) {
	 		ELObj.rowCreation(rwNum++);
	 		for(int j = 0;j<7;j++) {
	 			if(i==1) break;
	 			if(j==0) {
	 				ELObj.cellFormatting("hejldpmq");
	 				ELObj.cellCreationAndWrite(j,ColNme[++p]);
	 				ELObj.cellFormatting("rswt");
	 			}
	 			else {
	 				ELObj.cellCreationAndWrite(j,"");
	 				ELObj.cellFormatting("ldpwst");
	 			}
	 		}
	 		if(i==1) {
	 			
	 			ELObj.cellCreationAndWrite(0, CMObj.toSendDetails('j').toString());
	 			ELObj.cellFormatting("yst");
	 		}
	 	}
	 	rwNum +=2;
	 	List<WebElement> BwlrNme =  CMObj.toSendList('b');
	 	for(int i = 0;i<BwlrNme.size()+1;i++) {
	 		ELObj.rowCreation(rwNum++);
	 		if(i!=0) {
	 			plyrName = CMObj.toSendDetails('d',BwlrNme.get(i-1));
	 		}
	 		for(int j=0;j<8;j++) {
	 			if(j==0) 
	 				ELObj.cellFormatting("c");
				if(j==4) 
					ELObj.cellFormatting("j");
				
	 			if(i==0) {
	 				if(j==0||j==4) ELObj.cellFormatting("j");
					else ELObj.cellFormatting("u");
					if(j!=0) ELObj.cellFormatting("b");
					ELObj.cellFormatting("hemqr");
					ELObj.cellCreationAndWrite(j,ColNme[++p]);
	 				ELObj.cellFormatting("lpdzwst");
	 			}
	 			else {
	 				if(j==0) {
	 					ELObj.cellFormatting("igoqx");	
	 					ELObj.cellCreationAndWrite(j,plyrName);
	 					ELObj.cellFormatting("rswt");
	 					
	 				}
	 				else {
	 					ELObj.cellFormatting("bhe");
	 					ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('c',plyrName,j));
	 					ELObj.cellFormatting("rswt");
	 				}
	 			}
	 		}
	 	}
	 	
	 	for(int i = 0; i<=1; i++)
	 		ELObj.rowCreation(rwNum++);
	 	
	 	//Create "Powerplay" Column Header in Excel
	 	for(int j=0;j<8;j++) {
	 		if(j==0) 
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('k').toString());
	 		else if(j==4)
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('l').toString());
	 		else if(j==7)
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('m').toString());
	 		else
	 			ELObj.cellCreationAndWrite(j,"");
	 	}
	 	ELObj.rowCreation(rwNum++);
	 	
	 	//Create "Powerplay" Details in Excel
	 	for(int j=0;j<8;j++) {
	 		if(j==0) 
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('n').toString());
	 		else if(j==4)
	 			ELObj.cellCreationAndWrite(j,CMObj.toSendDetails('o').toString());
	 		else if(j==7)
	 			ELObj.cellCreationAndWrite(j, CMObj.toSendDetails('p').toString());
	 		else
	 			ELObj.cellCreationAndWrite(j,"");
	 	}
	 	System.out.println("*****"+ status +"*****");
	 	ELObj.fileCreation();
	 	
	 }
}
