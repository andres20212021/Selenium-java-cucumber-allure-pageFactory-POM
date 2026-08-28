package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {

    @FindBy(id = "cartur")
    private WebElement cartLink;

    public void goToCart() {
        click(cartLink);
    }
}
