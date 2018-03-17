package cn.gloryroad.testScript;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static cn.gloryroad.util.WaitUitl.*;

import cn.gloryroad.util.Log;
import cn.gloryroad.util.WaitUitl;

public class TestSendMailWithAttachment {
	
	WebDriver driver;
	String baseUrl;
	
	@Test
	public void testSendMailWithAttachment() {
		DOMConfigurator.configure("log4j.xml");
		//访问测试网站http://mail.126.com
		driver.get("http://mail.126.com");
		driver.manage().window().maximize();
		sleep(5000);
		WebElement iframe=driver.findElement(By.id("x-URS-iframe"));
		driver.switchTo().frame(iframe);
		sleep(5000);
		System.out.println("------------------------------------------------");
		System.out.println(driver.getPageSource());
		System.out.println("------------------------------------------------");
		//获取邮箱元素
		
		WebElement userName=driver.findElement(By.xpath("//*[@data-loginname='loginEmail']"));
		
		WaitUitl.waitWebElement(driver, "//*[@data-loginname='loginEmail']");
		//登录邮箱输入密码
		sleep(2000);
		WebElement passWord=driver.findElement(By.xpath("//*[@name='password']"));
		sleep(2000);
		//登录首页的登录按钮
		WebElement loginButton=driver.findElement(By.id("dologin"));
		sleep(2000);

		
		//输入邮箱登录名
		userName.sendKeys("asdff_px");
		//输入密码
		passWord.sendKeys("Hjnpx831003**");
		//点击登录按钮
		loginButton.click();
		
		sleep(2000);
		//确定登录按钮
		WebElement loginAffirmButton=driver.findElement(By.xpath("//*[@data-action='myphonegoon']"));
		sleep(2000);
		//再次点击登录按钮
		loginAffirmButton.click();
		sleep(3000);
		//调用封装的显示等待函数，在页面显示出退出链接后，继续执行后续代码逻辑
		WaitUitl.waitWebElement(driver, "//li[@title=\"通讯录\"]");
		
		//找写信元素
		WebElement writeMailLink=driver.findElement(By.xpath("//span[text()='写 信']"));
		sleep(1000);
		//点击写信
		writeMailLink.click();
		sleep(2000);
		//waitWebElement(driver, "//a[text()='收件人'");
		
		WaitUitl.waitWebElement(driver, "//span[text()='写 信']");
		sleep(3000);
		//定位写信页面的收件人输入框
		//WebElement recipients=driver.findElement(By.xpath("//*[contains(@id,'_mail_emailinput_0')/input"));
		Log.debug("-----------------------------------");
		
		Log.debug(driver.getPageSource());
		Log.debug("-----------------------------------");
		WebElement recipients=driver.findElement(By.xpath("//div[@id='_mail_emailcontainer_0']"));
		sleep(2000);
		//定拉主题 输入框
		WebElement mailSubject=driver.findElement(By.xpath("//[contains(@id,'_subjectInput')]"));
		sleep(2000);
		//输入收件人地址
		recipients.sendKeys("hjnpx@126.com");
		sleep(2000);
		//输入主题信息
		mailSubject.sendKeys("这是一封自动化测试邮件");
		
		System.out.println("运行结束");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver", "/XPENG/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type", "--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
