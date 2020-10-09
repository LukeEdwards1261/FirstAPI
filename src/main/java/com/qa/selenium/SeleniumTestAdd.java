package com.qa.selenium;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestAdd {
	
	//ATTENTION, iN ORDER FOR ALL SELENIUM TESTS TO WORK, A SINGLE ENTRY MUST BE ADDED FIRST OR FOR THEM TO BE RAN IN ORDER (SEE @TEST(POSITION) FOR THE ORDER), THIS CAN BE DONE WITHIN THE HTML PAGE OR VIA POSTMAN 
	

    private static WebDriver driver;
    

    @BeforeAll
    public static void setup() {
    	System.out.print("add");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luke Edwards\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\FirstAPI\\src\\main\\resources\\chromedriver.exe");
//        driver = new ChromeDriver(chromeCfg());     //add this line instead of the one below if using google to stop its popups, otherwise use normal as this can block needed popups
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 1168));
        
        

    }

    @Test
    @Order(1) //FIRST
    public void test() throws InterruptedException {
        driver.get("http://127.0.0.1:5500/html/October2020.html");
        Thread.sleep(3000);
        WebElement AddToTable = driver.findElement(By.xpath("/html/body/div[2]/button[2]"));
        AddToTable.click();
        Thread.sleep(3000);
        WebElement Title = driver.findElement(By.xpath("//*[@id=\"createTitle\"]"));
        Title.sendKeys("ASDA");
        Thread.sleep(1000);
        WebElement Task = driver.findElement(By.xpath("//*[@id=\"createTask\"]"));
        Task.sendKeys("get milk and eggs");
        Thread.sleep(1000);
        WebElement Type = driver.findElement(By.xpath("//*[@id=\"createType\"]"));
        Type.sendKeys("shopping");
        Thread.sleep(1000);
        WebElement SaveChanges = driver.findElement(By.xpath("//*[@id=\"createOctTable\"]"));
        SaveChanges.click();
        Thread.sleep(1000);
        Thread.sleep(5000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.navigate().refresh();


        String test = driver.findElement(By.xpath("//*[@id=\"week1\"]/thead/tr[3]/td[3]")).getText();
        assertEquals("get milk and eggs",test);
       
    }
    


    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}


