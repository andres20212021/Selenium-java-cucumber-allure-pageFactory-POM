package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(css = "h2.name")
    private WebElement productName;

    @FindBy(css = "h3.price-container")
    private WebElement productPrice;

    public void addProductToCart() {
        click(addToCartButton);
    }

    public void acceptMessageConfirmation() {
        acceptAlert();
    }

    public String getProductName() {
        return getText(productName).trim();
    }

    public int getProductPrice() {

        String priceText = getText(productPrice);

        priceText = priceText
                .replace("$", "")
                .replace("*includes tax", "")
                .trim();

        return Integer.parseInt(priceText);
    }

}
