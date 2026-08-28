package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private static final int TIMEOUT_SECONDS = 10;

    protected BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    private WebDriverWait explicitWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    protected void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    // Unico punto del framework que aun localiza por By: solo se usa para
    // elementos dinamicos (armados con un parametro en tiempo de ejecucion),
    // que @FindBy no puede expresar al ser una anotacion estatica.
    protected WebElement dynamicElement(By locator) {
        try {
            return explicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException ex) {
            return Assertions.fail("Timeout: El elemento no se hizo visible a tiempo: " + locator);
        }
    }

    protected void click(WebElement element) {
        try {
            explicitWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (TimeoutException ex) {
            Assertions.fail("Timeout: El elemento no estuvo disponible para hacer click: " + element);
        }
    }

    protected void write(WebElement element, String text) {
        try {
            explicitWait().until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
        } catch (TimeoutException ex) {
            Assertions.fail("Timeout: El elemento no se hizo visible a tiempo: " + element);
        }
    }

    protected String getText(WebElement element) {
        try {
            explicitWait().until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (TimeoutException ex) {
            return Assertions.fail("Timeout: El elemento no se hizo visible a tiempo: " + element);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            explicitWait().until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (TimeoutException ex) {
            return Assertions.fail("Timeout: El elemento no se hizo visible a tiempo: " + element);
        }
    }

    protected List<WebElement> getElements(List<WebElement> elements) {
        try {
            explicitWait().until(ExpectedConditions.visibilityOfAllElements(elements));
            return elements;
        } catch (TimeoutException ex) {
            return Assertions.fail("Timeout: Los elementos no se hicieron visibles a tiempo.");
        }
    }

    protected void waitUntilNumberOfElementsChanges(List<WebElement> elements, int previousCount) {
        try {
            explicitWait().until(driver -> elements.size() != previousCount);
        } catch (TimeoutException ex) {
            Assertions.fail("Timeout: La cantidad de elementos no cambio.");
        }
    }

    protected void acceptAlert() {
        Alert alert = explicitWait().until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
