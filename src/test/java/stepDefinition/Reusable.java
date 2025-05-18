package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Reusable {



    public void loginApplication(WebDriver driver, String  username, String password){
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }
}
