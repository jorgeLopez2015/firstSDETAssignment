package stepDefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SauceDemo {

    WebDriver driver;

    Reusable rp;

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    @Before
    public void luanchApplication(){

        rp = new Reusable();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");

        Map prefs = new HashMap();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") +"/Saucelab.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "Kubernetes - Home Page");
        spark.config().setDocumentTitle("Kubernetes Test");
        spark.config().setReportName("Testing the Kubernetes page");
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Working on TestNG Assignment app using Selenium TestNG");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        logger.info("The User is launching the SauceLab site");
    }

    @When("User gives correct valid credential")
    public void user_gives_correct_valid_credential() {
        rp.loginApplication(driver,"standard_user", "secret_sauce");
        logger.pass("The Valid credentials step is successfully done");
    }
    @Then("User can see SauceLab welcome page")
    public void user_can_see_sauce_lab_welcome_page() {
        String title = driver.getTitle();
        System.out.println("the title: "+title);
        Assert.assertEquals(title, "Swag Labs", "The user is not seeing the Welcome page, check credentials");
        logger.pass("The Soucelab welcome page step is successfully done");
    }
    @When("User clicks on hamburger menu")
    public void user_clicks_on_hamburger_menu() {
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        logger.pass("The User clicks on hamburger menu step is successfully done");
    }
    @Then("User can see logout link")
    public void user_can_see_logout_link() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"))));
        Assert.assertTrue(driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).isDisplayed());
        logger.pass("The User can see the logout menu step is successfully done");
    }
    @When("User clicks on logout link")
    public void user_clicks_on_logout_link() {
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        logger.pass("The User clicks on logout link step is successfully done");
    }
    @Then("User successfully log out from the application")
    public void user_successfully_log_out_from_the_application() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/");
        logger.pass("The User is logged out step is successfully done");
    }
    @Then("User quits SauceDemo application")
    public void user_quits_sauce_demo_application() {
        driver.quit();
        logger.pass("The closing application step is successfully done");
    }

    @Then("User selects three items")
    public void user_selects_three_items() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
        logger.pass("The User selecting three items step is successfully done");
    }
    @Then("User can see three count in Cart icon")
    public void user_can_see_three_count_in_cart_icon() {
        String count = driver.findElement(By.xpath("//span[@data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(count, "3", "The cart contains different than 3 products");
        logger.pass("Checking the cart count step is successfully done");
    }
    @When("User clicks on Cart icon")
    public void user_clicks_on_cart_icon() {
        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
        logger.pass("The User clicking on Cart icon step is successfully done");
    }
    @Then("User moves to Cart page")
    public void user_moves_to_cart_page() {
        //Assert.assertTrue(driver.findElement(By.xpath("//span[@data-set='title']")).isDisplayed());
        String title = driver.findElement(By.xpath("//span[@data-test='title']")).getText();
        Assert.assertEquals(title, "Your Cart", "The User is not in the Cart page");
        logger.pass("The User seeing the Cart page step is successfully done");
    }
    @Then("User clicks on Checkout button")
    public void user_clicks_on_checkout_button() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        logger.pass("The User clicking on Checkout button step is successfully done");
    }
    @When("User fills customer details")
    public void user_fills_customer_details() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='first-name']"))));
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Jones");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("503");
        logger.pass("The User completing the customer details step is successfully done");
    }
    @Then("User clicks on Continue button")
    public void user_clicks_on_continue_button() {
        driver.findElement(By.xpath("//input[@id='continue']")).click();
        logger.pass("The User clicking on Continue button step is successfully done");
    }
    @Then("User completes the order")
    public void user_completes_the_order() {
        String overview = driver.findElement(By.xpath("//span[@data-test='title']")).getText();
        Assert.assertEquals(overview, "Checkout: Overview", "The User did not click on Continue button from previous step");
        logger.pass("The User completing the order step is successfully done");
    }

    @When("Login with username as {string} and {string}")
    public void login_with_username_as_and(String username, String password) {
        rp.loginApplication(driver,username, password);
        logger.pass("The Valid credentials step is successfully done");
    }

    @After
    public void closeApplication(){
        driver.quit();
        logger.pass("The closing the application step is successfully done");
    }
}
