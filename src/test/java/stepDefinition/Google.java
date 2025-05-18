package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Google {

    WebDriver driver;

    @Given("User launch Google application")
    public void user_launch_google_application() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }
    @When("User can see Google home page")
    public void user_can_see_google_home_page() {
        String title = driver.getTitle();
        System.out.println("Title: "+title);
        if(title.equalsIgnoreCase("Google")){
            System.out.println("The User is seeing the Google home page");
        }

    }
    @Then("User validates Google logo")
    public void user_validates_google_logo() {
        if(driver.findElement(By.xpath("//*[@class='lnXdpd']")).isDisplayed()){
            System.out.println("Google logo is present");
        }

    }
    @Then("User validates Google search box")
    public void user_validates_google_search_box() {
        if(driver.findElement(By.xpath("//textarea[@title='Buscar']")).isDisplayed()){
            System.out.println("The search box is displayed");
        }

    }
    @Then("User closes Google application")
    public void user_closes_google_application() {
        driver.quit();
    }

}
