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


public class SeleniumTestDelete {
	

    private static WebDriver driver;
    


    @BeforeAll
    public static void setup() {
    	System.out.print("Update");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Luke Edwards\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\FirstAPI\\src\\main\\resources\\chromedriver.exe");
//        driver = new ChromeDriver(chromeCfg());     //add this line instead of the one below if using google to stop its popups, otherwise use normal as this can block needed popups
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 1168));
        
        

    }

    @Test
    @Order(3)
    public void test() throws InterruptedException {
        driver.get("http://127.0.0.1:5500/html/October2020.html");
        Thread.sleep(3000);
        WebElement DeleteOne = driver.findElement(By.xpath("//*[@id=\"deletetask1\"]"));
        DeleteOne.click();        
        Thread.sleep(5000);
        driver.navigate().refresh();


        String test = driver.findElement(By.xpath("//*[@id=\"week1\"]/thead/tr[2]/td[1]")).getText();
        assertEquals("2",test);
        
    }
    
    
    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}







