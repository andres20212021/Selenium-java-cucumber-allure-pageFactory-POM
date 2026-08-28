package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "country")
    private WebElement countryInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "card")
    private WebElement creditCardInput;

    @FindBy(id = "month")
    private WebElement monthInput;

    @FindBy(id = "year")
    private WebElement yearInput;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(xpath = "//h2[text()='Thank you for your purchase!']")
    private WebElement successTitle;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//p[contains(@class,'lead')]")
    private WebElement successDetails;

    public boolean productVisibleInCart(String productName) {
        return isElementDisplayed(dynamicElement(By.xpath("//td[normalize-space(.)='" + productName + "']")));
    }

    public int getProductPriceInCart(String productName) {
        By priceLocator = By.xpath("//tbody[@id='tbodyid']//tr[td[normalize-space()='" + productName + "']]/td[3]");
        String priceText = getText(dynamicElement(priceLocator));
        return Integer.parseInt(priceText.trim());
    }

    public void clickPlaceOrder() {
        click(placeOrderButton);
    }

    public void fillPurchaseForm(String name, String country, String city, String creditCard, String month, String year) {
        write(nameInput, name);
        write(countryInput, country);
        write(cityInput, city);
        write(creditCardInput, creditCard);
        write(monthInput, month);
        write(yearInput, year);
    }

    public void clickPurchase() {
        click(purchaseButton);
    }

    public String getSuccessTitle() {
        return getText(successTitle).trim();
    }

    public String getSuccessDetails() {
        return getText(successDetails).trim();
    }

}
