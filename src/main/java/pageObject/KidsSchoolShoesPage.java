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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KidsSchoolShoesPage {

    WebDriver driver;

    public KidsSchoolShoesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/collections/kids']")
    WebElement kidsMenuOption;

    @FindBy(xpath = "//a[@href='/collections/shoes']")
    WebElement shoesSubmenuOption;

    @FindBy(xpath = "(//a[@href='/collections/kids-school-shoes'])[2]")
    WebElement shoesSchoolOption;

    public void navigatingOption(){
        try{
            Actions action = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(kidsMenuOption));
            action.moveToElement(kidsMenuOption).perform();
            wait.until(ExpectedConditions.visibilityOf(shoesSubmenuOption));
            action.moveToElement(shoesSubmenuOption).perform();
            wait.until(ExpectedConditions.visibilityOf(shoesSchoolOption));
            shoesSchoolOption.click();
        }catch (Exception e){e.printStackTrace();}
    }

    public void checkingShoesName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> titleElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".CollectionInner__Products .ProductItem__Title")));

        List<Double> titlesName = new ArrayList<>();

        for(int i = 0; i < titleElements.size(); i++){
            String nameProduct = titleElements.get(i).getText();
            System.out.println("Name product: "+nameProduct);
            Assert.assertTrue(nameProduct.contains("SCHOOL"), "The title does not have SCHOOL");
        }
        System.out.println("All the names of shoes contains SCHOOL");
    }
}
