package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='customer[email]']")
    WebElement userEmail;

    @FindBy(xpath = "//input[@name='customer[password]']")
    WebElement passwordUser;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@href='/account/register']")
    WebElement createOneLink;

    @FindBy(xpath = "//img[@alt='Sreeleathers Ltd']")
    WebElement logoPage;

    public void loginPage(String invalidUser, String invalidPassword){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Login']")));
        Assert.assertTrue(userEmail.isDisplayed(), "The User textbox is not displayed");
        Assert.assertTrue(passwordUser.isDisplayed(), "The Password textbox is not displayed");
        Assert.assertTrue(loginButton.isDisplayed(), "The login button is not displayed");
        Assert.assertTrue(createOneLink.isDisplayed(), "The Create one link is not displayed");
        System.out.println("All the web elements from Login page are displayed");
        userEmail.sendKeys(invalidUser);
        passwordUser.sendKeys(invalidPassword);
        loginButton.click();
    }

    public void clickOnLogo(){
        logoPage.click();
    }




}
