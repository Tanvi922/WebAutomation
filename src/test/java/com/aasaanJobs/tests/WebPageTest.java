package com.aasaanJobs.tests;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class WebPageTest {

    public WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    int s1 = randInt(111111 ,999999);
    public String number = "2211" + s1;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://release.aasaanjobs.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void testWebPage() throws Exception {
        try{
            driver.get(baseUrl + "en/");
            assertEquals("Great opportunity for all freshers & experienced looking out for jobs ! AasaanJobs", driver.getTitle());
            searchJobs();
            loginForNewUser();
            employerZone();
            loginForExistingUser();
            consoleErrorLog();}
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception did occur:" + ex);
            captureScreenShot();
        }
    }
    public void searchJobs() throws InterruptedException {
        driver.findElement(By.id("job-profile-autocomplete")).clear();
        driver.findElement(By.id("job-profile-autocomplete")).sendKeys("Driver");
        driver.findElement(By.xpath("//span/div/div[2]/div")).click();
        driver.findElement(By.id("search-submit")).click();
        assertEquals("Find Driver Jobs in Mumbai, Maharashtra, India | Aasaanjobs", driver.getTitle());
        Thread.sleep(100l);
        driver.navigate().back();
    }
    public void loginForNewUser() throws InterruptedException {
        driver.get("https://release.aasaanjobs.com/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.linkText("LOGIN/REGISTER")).click();
        assertEquals("Welcome To Aasaanjobs", driver.findElement(By.id("gridSystemModalLabel")).getText());
        driver.findElement(By.id("id_emailOrMobile")).clear();
        driver.findElement(By.id("id_emailOrMobile")).sendKeys("1234567890");
        driver.findElement(By.id("login_new")).click();
        driver.findElement(By.id("id_password")).clear();
        driver.findElement(By.id("id_password")).sendKeys("12");
        driver.findElement(By.id("login_new")).click();
//        assertEquals("Username And Password Do Not Match.", driver.findElement(By.id("login-err-msg")).getText());
        driver.findElement(By.id("id_emailOrMobile")).clear();
        driver.findElement(By.id("id_password")).clear();
        driver.findElement(By.id("login_new")).click();
        assertEquals("Mobile no. is required", driver.findElement(By.id("login-err-msg")).getText());
        driver.findElement(By.id("id_emailOrMobile")).clear();
        driver.findElement(By.id("id_emailOrMobile")).sendKeys("tanviJoshi");
        driver.findElement(By.id("login_new")).click();
        assertEquals("Mobile no. should be Integer", driver.findElement(By.id("login-err-msg")).getText());
        driver.findElement(By.id("id_emailOrMobile")).clear();
        driver.findElement(By.id("id_emailOrMobile")).sendKeys("80802550");
        driver.findElement(By.id("login_new")).click();
        assertEquals("Mobile no. should be of 10 character only", driver.findElement(By.id("login-err-msg")).getText());
        driver.findElement(By.id("id_emailOrMobile")).clear();
        driver.findElement(By.id("id_emailOrMobile")).sendKeys(number);
        System.out.println("Number is" + number);
        driver.findElement(By.xpath("//form[@id='login']/div[2]/div/div/div/div/div[2]/div/div/div/ins")).click();
        driver.findElement(By.id("login_new")).click();
        assertEquals("Password is required", driver.findElement(By.id("login-err-msg")).getText());
        assertEquals("", driver.findElement(By.xpath("//form[@id='login']/div[2]/div/div/div/div/div[2]/div/div/div/ins")).getText());
        driver.findElement(By.id("id_password")).clear();
        driver.findElement(By.id("id_password")).sendKeys("12");
        driver.findElement(By.id("login_new")).click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        Thread.sleep(500l);
        WebDriverWait wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.text-center")));
        assertEquals("Verification Code | Aasaanjobs", driver.getTitle());
        assertEquals("Please enter the verification code received on your mobile", driver.findElement(By.cssSelector("h2.text-center")).getText());
        driver.findElement(By.id("submit-btn")).click();
        assertEquals("This field is required.", driver.findElement(By.cssSelector("li")).getText());
        driver.findElement(By.cssSelector("label.placeholder")).click();
        driver.findElement(By.id("id_otp1")).clear();
        driver.findElement(By.id("id_otp1")).sendKeys("570");
        driver.findElement(By.cssSelector("label.placeholder")).click();
        driver.findElement(By.cssSelector("label.placeholder")).click();
        driver.findElement(By.id("submit-btn")).click();
        assertEquals("Issue In OTP Validation", driver.findElement(By.cssSelector("li")).getText());
        driver.findElement(By.cssSelector("label.placeholder")).click();
        driver.findElement(By.id("id_otp1")).clear();
        driver.findElement(By.id("id_otp1")).sendKeys("5703");
        driver.findElement(By.id("id_otp1")).click();
        driver.findElement(By.id("submit-btn")).click();
        driver.findElement(By.cssSelector("label.placeholder")).click();
        driver.findElement(By.id("id_otp1")).clear();
        driver.findElement(By.id("id_otp1")).sendKeys("5");
        driver.findElement(By.id("id_otp1")).click();
        driver.findElement(By.xpath("//form[@id='form-signin']/div[3]/div")).click();
        driver.findElement(By.id("id_otp1")).clear();
        driver.findElement(By.id("id_otp1")).sendKeys("54584");
        driver.findElement(By.id("id_otp1")).click();
        driver.findElement(By.id("submit-btn")).click();
        driver.findElement(By.id("submit-btn")).click();
        driver.findElement(By.cssSelector("label.placeholder")).click();
        driver.findElement(By.id("id_otp1")).clear();
        driver.findElement(By.id("id_otp1")).sendKeys("57030");
        driver.findElement(By.id("submit-btn")).click();
        driver.close();
    }
    public void employerZone() throws AWTException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl + "en/");
        driver.findElement(By.xpath("//a[contains(text(),'Employer Zone')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Recruitment')]")).click();
        assertEquals("Hire freshers as well Experienced across all locations in India only at AasaanJobs", driver.getTitle());
        assertEquals("Choose solutions customisable for your needs", driver.findElement(By.xpath("//div[@id='permanent-staffing']/div/div/div/span")).getText());
        driver.findElement(By.xpath("//a[contains(text(),'Staffing')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Post a free job')]")).click();
        assertEquals("Post A Job | Aasaanjobs | Better jobs, Better life", driver.getTitle());
        WebDriverWait wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div[3]/div/div/div[3]/div/form/div[1]/div/div/div/div[1]")));
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/div/div/div[3]/div/form/div[1]/div/div/div/div[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div/div/div/div/div[2]/div/div[3]")));
        driver.findElement(By.xpath("//form/div/div/div/div/div[2]/div/div[3]")).click();
        driver.findElement(By.name("openings")).clear();
        driver.findElement(By.name("openings")).sendKeys("4");
        driver.findElement(By.name("jobTitle")).clear();
        driver.findElement(By.name("jobTitle")).sendKeys("Testing");
        driver.findElement(By.name("jobDesc")).clear();
        driver.findElement(By.name("jobDesc")).sendKeys("test123");
        driver.findElement(By.xpath("//div[4]/div/div/div/div/label")).click();
        driver.findElement(By.name("jobDesc")).clear();
        driver.findElement(By.name("jobDesc")).sendKeys("Automation testing with selenium webdriver\nusing Java,");
        driver.findElement(By.name("jobDesc")).clear();
        driver.findElement(By.name("jobDesc")).sendKeys("Automation testing with selenium webdriver nusing Java,Experience in Testing Mobile apps on various operatings e.g. iOS, Android, Windows etc using various devices e.g. Apple, Blackberry, Nokia, Android etc\nExperience in Mobile Application testing");
        driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
        driver.findElement(By.xpath("//div/div/div/div/div[2]/div/div/div/div/div")).click();
        driver.findElement(By.xpath("//div[2]/div[3]/div/div/div/div")).click();
        driver.findElement(By.linkText("PM")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'AM')])[2]")).click();
        driver.findElement(By.id("autocomplete text0")).clear();
        driver.findElement(By.id("autocomplete text0")).sendKeys("Mumbai");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        driver.findElement(By.id("min-salary-spin")).clear();
        driver.findElement(By.id("min-salary-spin")).sendKeys("4");
        assertEquals(" Maximum salary cannot be smaller than the minimum salary.", driver.findElement(By.xpath("//div[4]/span")).getText());
        driver.findElement(By.id("max-salary-spin")).clear();
        driver.findElement(By.id("max-salary-spin")).sendKeys("6");
        driver.findElement(By.xpath("//div[3]/div/div[2]/div/div/div/div/div")).click();
        driver.findElement(By.xpath("//li[3]/label/div/ins")).click();
        driver.findElement(By.cssSelector("div.icheckbox_flat.hover > ins.iCheck-helper")).click();
        driver.findElement(By.xpath("//li[3]/label/div/ins")).click();
        driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
        driver.findElement(By.xpath("//div[4]/div/div/div/div/div/a/span")).click();
    }
    public void loginForExistingUser(){
        driver.findElement(By.id("login_new")).click();
        assertEquals("Mobile no. is required", driver.findElement(By.id("login-err-msg")).getText());
        driver.findElement(By.id("id_emailOrMobile")).clear();
        driver.findElement(By.id("id_emailOrMobile")).click();
        driver.findElement(By.id("id_emailOrMobile")).sendKeys("8080255053");
        driver.findElement(By.id("login_new")).click();
        assertEquals("Password is required", driver.findElement(By.id("login-err-msg")).getText());
        driver.findElement(By.id("id_password")).clear();
        driver.findElement(By.id("id_password")).sendKeys("12");
        driver.findElement(By.id("login_new")).click();
        assertEquals("Telecaller/ BPO - [Outbound]", driver.findElement(By.xpath("//div[@id='jobListPage']/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/span")).getText());
        assertEquals("My Jobs", driver.findElement(By.xpath("//div[@id='jobListPage']/div/div/div/div/div/h3")).getText());
        assertEquals("10:00 AM - 06:00 PM", driver.findElement(By.xpath("//div[@id='jobListPage']/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div[2]/div/span")).getText());
        assertEquals("Kandivali West, Mumbai", driver.findElement(By.xpath("//div[@id='jobListPage']/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div/span")).getText());
        driver.findElement(By.xpath("//a[contains(text(),'abc xyz')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        return randomNum;
    }

    public void consoleErrorLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }
    public void captureScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\screenshot.png"));
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
