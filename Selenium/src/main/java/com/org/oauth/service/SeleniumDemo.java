package com.org.oauth.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SeleniumDemo {

	public static void main(String[] args) {

		String baseURL = "http://www.gmail.com";
		String email = "ramesh.krathod1@gmail.com";
		String password = "9164000290";

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kelltontech\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(baseURL);
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_email")));

		driver.findElement(By.id("login_email")).sendKeys(email);
		driver.findElement(By.id("login_password")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='login']/a")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@id='nav']/div/div[1]/div/div[4]/div/a/div[1]")));
		// Enter code here to perform functions after login

	}

}
