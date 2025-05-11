package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@alt='Sreeleathers Ltd']")
    WebElement logoPage;

    @FindBy(xpath = "(//a[contains(@href,'/account')])[2]")
    WebElement loginButton;

    @FindBy(xpath = "(//a[text()='Home'])[2]")
    WebElement homeMenuOption;

    @FindBy(xpath = "//a[@href= '/collections/men']")
    WebElement menMenuOption;

    @FindBy(xpath = "//a[@href= '/collections/women']")
    WebElement womenMenuOption;

    @FindBy(xpath = "//a[@href='/collections/kids']")
    WebElement kidsMenuOption;

    @FindBy(xpath = "//a[@href= '/collections/accessories']")
    WebElement accessoriesMenuOption;

    @FindBy(xpath = "//a[@href= '/collections/wallet']")
    WebElement walletSubMenuOption;

    @FindBy(xpath = "(//a[@href= '/collections/mens-wallet'])[2]")
    WebElement mensWalletSubOption;

    @FindBy(xpath = "(//a[@href= '/collections/washable'])[2]")
    WebElement washableMenuOption;


    public void clickLoginIcon(){
        loginButton.click();
    }

    public void getLogoAttribute(){
        String value = logoPage.getAttribute("src");
        System.out.println("The src value is: "+value);
    }

    public void validatingMenuOptions(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(homeMenuOption));
        Assert.assertTrue(homeMenuOption.isDisplayed(), "The Home menu option is not displayed");
        wait.until(ExpectedConditions.elementToBeClickable(menMenuOption));
        Assert.assertTrue(menMenuOption.isDisplayed(), "The Men menu option is not displayed");
        wait.until(ExpectedConditions.elementToBeClickable(womenMenuOption));
        Assert.assertTrue(womenMenuOption.isDisplayed(), "The Women menu option is not displayed");
        wait.until(ExpectedConditions.elementToBeClickable(kidsMenuOption));
        Assert.assertTrue(kidsMenuOption.isDisplayed(), "The Kids menu option is not displayed");
        wait.until(ExpectedConditions.elementToBeClickable(accessoriesMenuOption));
        Assert.assertTrue(accessoriesMenuOption.isDisplayed(), "The Accessories menu option is not displayed");
        wait.until(ExpectedConditions.elementToBeClickable(washableMenuOption));
        Assert.assertTrue(washableMenuOption.isDisplayed(), "The Washable menu option is not displayed");
        System.out.println("All the options from Header menu are displayed");
    }

    public void validateLogo(){
        if(logoPage.isDisplayed()){
            System.out.println("The Sreeleathers logo is displayed");
        }
    }

    public void verifyingHomePage(){
        String urlHome = driver.getCurrentUrl();
        if(urlHome.equals("https://sreeleathers.com/")){
            System.out.println("The User is in Home Page");
        }
    }

    public void navigatingOption(){
        try{
            Actions action = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(accessoriesMenuOption));
            //new Actions(driver).moveToElement(walletSubMenuOption).perform();
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href= '/collections/mens-wallet']")));
            //new Actions(driver).moveToElement(mensWalletSubOption).perform();
            action.moveToElement(accessoriesMenuOption).perform();
            wait.until(ExpectedConditions.visibilityOf(walletSubMenuOption));
            action.moveToElement(walletSubMenuOption).perform();
            wait.until(ExpectedConditions.visibilityOf(mensWalletSubOption));
            mensWalletSubOption.click();

        }catch (Exception e){e.printStackTrace();}
    }



}
