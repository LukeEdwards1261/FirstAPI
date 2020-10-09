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


public class SeleniumTestUpdate {
	

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
    @Order(2)
    public void test() throws InterruptedException {
        driver.get("http://127.0.0.1:5500/html/October2020.html");
        Thread.sleep(3000);
        WebElement ViewUpdate = driver.findElement(By.xpath("//*[@id=\"week1\"]/thead/tr[2]/td[5]/a"));
        ViewUpdate.click();
        Thread.sleep(3000);
        WebElement Title = driver.findElement(By.xpath("//*[@id=\"taskTitle\"]"));
        Title.clear();
        Title.sendKeys("Tesco");
        Thread.sleep(1000);
        WebElement Task = driver.findElement(By.xpath("//*[@id=\"task\"]"));
        Task.clear();
        Task.sendKeys("get chocolate");
        Thread.sleep(1000);
        WebElement Type = driver.findElement(By.xpath("//*[@id=\"taskType\"]"));
        Type.clear();
        Type.sendKeys("shopping");
        Thread.sleep(1000);
        WebElement Update = driver.findElement(By.xpath("/html/body/form/button"));
        Update.click();
        Thread.sleep(1000);
        WebElement GoBack = driver.findElement(By.xpath("/html/body/form/a"));
        GoBack.click();
        Thread.sleep(5000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.navigate().refresh();


        String test = driver.findElement(By.xpath("//*[@id=\"week1\"]/thead/tr[2]/td[3]")).getText();
        assertEquals("get chocolate",test);
       
    }
    


    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}