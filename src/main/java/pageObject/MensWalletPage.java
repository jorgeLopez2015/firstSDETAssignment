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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MensWalletPage {

    WebDriver driver;

    public MensWalletPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Sort')]")
    WebElement sortByButton;

    @FindBy(xpath = "//button[@data-value='price-descending']")
    WebElement priceDescendingOption;

    @FindBy(xpath = "//button[@data-action='add-to-cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "(//a[@href='/collections/mens-wallet/products/men-leather-wallet-29540'])[2]")
    WebElement menWalletItem;

    @FindBy(xpath = "//span[@class='CartItem__Price Price']")
    WebElement pricePriceCart;

    @FindBy(xpath = "//button[@name='checkout']")
    WebElement checkoutButton;

    @FindBy(xpath = "(//input[@class='QuantitySelector__CurrentQuantity'])[1]")
    WebElement quantityProduct;

    @FindBy(xpath = "//a[@data-quantity='2']")
    WebElement plusIcon;

    @FindBy(xpath = "(//button[@name='checkout']/span)[3]")
    WebElement priceCheckoutButton;

    @FindBy(css = ".CartItem__Remove")
    WebElement removeLink;

    @FindBy(xpath = "//button[@data-drawer-id='sidebar-cart']")
    WebElement closeCartButton;

    public void clickingPriceDescending(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(sortByButton));
        sortByButton.click();
        wait.until(ExpectedConditions.visibilityOf(priceDescendingOption));
        priceDescendingOption.click();
    }

    public void verifyingPriceProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ProductItem__Price")));

        List<Double> prices = new ArrayList<>();

        for(int i = 0; i < Math.min(5, priceElements.size()); i++){
            String priceText = priceElements.get(i).getText().replaceAll("[^\\d]", "");
            System.out.println("Price product: "+priceText);
            double priceWallet = Double.parseDouble(priceText);
            prices.add(priceWallet);
        }

        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Collections.reverseOrder());
        Assert.assertEquals(prices, sortedPrices, "The prices are not ordered in descending order");
        System.out.println("The price are correctly ordered in descending mode");
    }

    public void addingProductToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(menWalletItem));
        String nameProduct = menWalletItem.getText();
        menWalletItem.click();
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        String name = driver.findElement(By.cssSelector(".ProductMeta__Title")).getText();
        Assert.assertTrue(nameProduct.equalsIgnoreCase(name), "The Product names are not the same");
    }

    public void addingQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(pricePriceCart));
        String priceText = pricePriceCart.getText().replaceAll("[^\\d]", "");
        int priceWallet = Integer.parseInt(priceText);
        String oquantity = quantityProduct.getAttribute("value");
        int quantity = Integer.parseInt(oquantity);
        Assert.assertEquals(quantity, 1, "The quantity is not the same");
        plusIcon.click();
        int newPrice = priceWallet*2;
        String flag ="RS. "+newPrice;
        wait.until(ExpectedConditions.textToBePresentInElement(priceCheckoutButton, flag));
        String npriceText = priceCheckoutButton.getText().replaceAll("[^\\d]", "");
        int nquantity = Integer.parseInt(npriceText);
        Assert.assertEquals(nquantity, newPrice, "Prices are not the same");
    }

    public void removingItemFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(removeLink));
        removeLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Cart__Empty")));
        String cartMessage = driver.findElement(By.cssSelector(".Cart__Empty")).getText();
        Assert.assertEquals(cartMessage, "YOUR CART IS EMPTY", "The Cart still have products");
        closeCartButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#sidebar-cart")));
        System.out.println("The cart is empty");
    }


}
