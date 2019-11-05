package com.qualitystream.tutorial;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class GoogleSearchTest {
    private WebDriver driver;
    By videoLinkLocator=By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']");
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGooglepage() {
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.clear();
        searchbox.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
        searchbox.submit();
        //Implicity wait
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Explicity wait
        // WebDriverWait ewait=new WebDriverWait(driver,10);
        //ewait.until(ExpectedConditions.titleContains("quality-stream"));

        //FluentWait
        Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement video=fwait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(videoLinkLocator);
            }
        });
        assertTrue(driver.findElement(videoLinkLocator).isDisplayed());
        //assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Google Search", driver.getTitle());
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
