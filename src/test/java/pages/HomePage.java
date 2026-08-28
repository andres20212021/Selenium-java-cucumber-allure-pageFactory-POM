package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = ".card.h-100")
    private List<WebElement> productCards;

    public void openDemoblaze() {
        navigateTo("https://www.demoblaze.com/index.html");
    }

    public void seleccionarCategoria(String name) {
        int cantidadAntesDelFiltro = getElements(productCards).size();
        click(dynamicElement(By.xpath("//a[@id='itemc' and normalize-space(.)='" + name + "']")));
        waitUntilNumberOfElementsChanges(productCards, cantidadAntesDelFiltro);
    }

    public int obtenerElementos() {
        return getElements(productCards).size();
    }

    public void seleccionarProducto(String productName) {
        click(dynamicElement(By.xpath("//a[normalize-space(.)='" + productName + "']")));
    }
}
