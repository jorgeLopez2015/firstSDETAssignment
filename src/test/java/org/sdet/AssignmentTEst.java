package org.sdet;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageObject.HomePage;
import pageObject.KidsSchoolShoesPage;
import pageObject.LoginPage;
import pageObject.MensWalletPage;

import java.time.Duration;

public class AssignmentTEst {

    LoginPage lp;
    WebDriver driver;
    HomePage hp;

    MensWalletPage mwp;

    KidsSchoolShoesPage ksp;
    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeClass
    @Parameters({"urlAssignment"})
    public void setUp(String urlAssignment){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") +"/KubernetesTest.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "Kubernetes - Home Page");
        spark.config().setDocumentTitle("Kubernetes Test");
        spark.config().setReportName("Testing the Kubernetes page");
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Working on TestNG Assignment app using Selenium TestNG");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(urlAssignment);
        logger.pass("The URL is successfully opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("The User is launching the Sreeleathers Kolkata site");
    }

    @Test
    @Parameters({"invalidUser", "invalidPassword"})
    public void EndToEndAssignment(String invalidUser, String invalidPassword){
        try {
            hp = new HomePage(driver);
            lp = new LoginPage(driver);
            mwp = new MensWalletPage(driver);
            ksp = new KidsSchoolShoesPage(driver);
            hp.validateLogo();
            logger.pass("The Logo validation is successfully done");
            hp.getLogoAttribute();
            logger.pass("The printing src value is successfully done");
            hp.clickLoginIcon();
            logger.pass("Clicking on login button is successfully done");
            lp.loginPage(invalidUser, invalidPassword);
            logger.pass("The Web elements of Login page is successfully done");
            lp.clickOnLogo();
            logger.pass("The clicking on Logo from Login page is successfully done");
            hp.verifyingHomePage();
            logger.pass("The Home verification is successfully done");
            hp.validatingMenuOptions();
            logger.pass("The Menu validation from Dashboard is successfully done");
            hp.navigatingOption();
            logger.pass("Clicking on Accessories > Wallets > Mens Wallet option");
            mwp.clickingPriceDescending();
            logger.pass("Ordering by Price descending");
            mwp.verifyingPriceProduct();
            logger.pass("The Price ordering in descending order is correct");
            mwp.addingProductToCart();
            logger.pass("The price was adder correctly");
            mwp.addingQuantity();
            logger.pass("The increasing quantity of Product is successfully done");
            mwp.removingItemFromCart();
            logger.pass("The removing product from cart is successfully done");
            ksp.navigatingOption();
            logger.pass("The navigation to School shoes is successfully done");
            ksp.checkingShoesName();
            logger.pass("The validation of the Shoes name is successfully done");

        }catch (Exception e){e.printStackTrace();}

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
