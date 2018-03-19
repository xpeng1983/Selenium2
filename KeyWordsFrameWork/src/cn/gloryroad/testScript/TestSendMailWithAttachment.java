package cn.gloryroad.testScript;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static cn.gloryroad.util.KeyBoardUtil.*;

import static cn.gloryroad.util.WaitUitl.*;

import cn.gloryroad.util.Log;
import cn.gloryroad.util.WaitUitl;

public class TestSendMailWithAttachment {

	WebDriver driver;
	String baseUrl;

	@Test
	public void testSendMailWithAttachment() {
		DOMConfigurator.configure("log4j.xml");
		// 访问测试网站http://mail.126.com
		driver.get("http://mail.126.com");
		driver.manage().window().maximize();
		sleep(5000);
		WebElement iframe = driver.findElement(By.id("x-URS-iframe"));
		driver.switchTo().frame(iframe);
		sleep(5000);
		// 获取邮箱元素

		// WebElement
		// userName=driver.findElement(By.xpath("//*[@data-loginname='loginEmail']"));
		WebElement userName = driver.findElement(By.xpath("//*[contains(@data-loginname,'loginEmail')]"));

		WaitUitl.waitWebElement(driver, "//*[@data-loginname='loginEmail']");
		// 登录邮箱输入密码
		sleep(2000);
		WebElement passWord = driver.findElement(By.xpath("//*[@name='password']"));
		sleep(2000);
		// 登录首页的登录按钮
		WebElement loginButton = driver.findElement(By.id("dologin"));
		sleep(2000);

		// 输入邮箱登录名
		userName.sendKeys("asdff_px");
		// 输入密码
		passWord.sendKeys("Hjnpx831003**");
		// 点击登录按钮
		loginButton.click();

		sleep(2000);
		// 确定登录按钮
		WebElement loginAffirmButton = driver.findElement(By.xpath("//*[@data-action='myphonegoon']"));

		sleep(2000);
		// 再次点击登录按钮
		loginAffirmButton.click();
		sleep(3000);
		// 调用封装的显示等待函数，在页面显示出退出链接后，继续执行后续代码逻辑
		WaitUitl.waitWebElement(driver, "//li[@title=\"通讯录\"]");
		// driver.findElement(By.xpath("//*[contains(@title,'通讯录')"));

		// 找写信元素
		WebElement writeMailLink = driver.findElement(By.xpath("//span[text()='写 信']"));
		// WebElement
		// writeMailLink=driver.findElement(By.xpath("//span[contains(text(),'写 信')"));

		sleep(1000);
		// 点击写信
		writeMailLink.click();
		sleep(2000);
		// waitWebElement(driver, "//a[text()='收件人'");

		WaitUitl.waitWebElement(driver, "//span[text()='写 信']");
		sleep(3000);

		// List<WebElement>
		// recipients=driver.findElements(By.xpath("//div[contains(@id,'_mail_emailcontainer_0')"));
		WebElement recipient = driver.findElement(By.xpath("//div[starts-with(@id,'_mail_emailinput_0')]/input"));
		// _mail_emailinput_0

		// System.out.println(recipients.size());
		sleep(2000);
		// 定拉主题 输入框

		sleep(2000);
		// 输入收件人地址
		recipient.sendKeys("hjnpx@126.com");
		sleep(3000);
		WebElement mailSubject = driver.findElement(By.xpath("//div[starts-with(@id,'_mail_input_2')]/input"));
		
		sleep(2000);
		// 输入主题信息
		mailSubject.sendKeys("这是一封自动化测试邮件");
		/*
		 * 调用KeyBoardUtil类中的pressTabKey方法，程序会执行按Tab键的操作 执行按Tab键操作后，页面的输入焦点自动切换到邮件正文的输入框
		 */
		sleep(3000);
		pressTabKey();
		/*
		 * 调用KeyBoardUtil类中的setAndCtrlClipboradData方法 模拟剪切板粘贴操作，将自定义的字符串内容粘贴入邮件正文输入框
		 */
		sleep(2000);
		setAndCtrlVClipboadrdData("这是一封自动化的测试邮件正文");
		// 将页面下滑到最后
		sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		// ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");

		sleep(2000);
		driver.findElements(By.xpath("//span[text()='发送']")).get(0).click();
		
		sleep(3000);
		
	//	WebElement mailSubject = driver.findElement(By.xpath("//div[starts-with(@id,'_mail_input_2')]/input"));
		
		waitWebElement(driver, "//h1[text()='发送成功']");
		
		sleep(2000);
		//断言页面中是否包含“发送成功”的信息，以此判断邮件是否发送成功
		Assert.assertTrue(driver.getPageSource().contains("发送成功"));
		//*[ends-with(@id,'_succInfo')]
		//1521295702417_succInfo
		// System.out.println(sendButtons.size());
		// *[@id="_mail_button_9_232"]/span[2]
		System.out.println("运行结束");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver", "/XPENG/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type", "--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		// driver.close();
	}
}
