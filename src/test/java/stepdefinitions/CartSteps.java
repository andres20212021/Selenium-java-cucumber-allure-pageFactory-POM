package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import io.cucumber.datatable.DataTable;
import java.util.Map;
import pages.CartPage;
import pages.HeaderPage;
import pages.HomePage;
import pages.ProductPage;

public class CartSteps {
    private int selectedProductPrice;
    private String selectedProductName;
    private String purchaseName;
    private String purchaseCountry;
    private String purchaseCity;
    private String purchaseCreditCard;
    private String purchaseMonth;
    private String purchaseYear;

    // Cada Page Object se crea en el momento en que se necesita dentro del
    // step (en vez de quedar como campo inicializado al construir la clase),
    // para que PageFactory.initElements() reciba siempre un WebDriver valido.

    @And("el usuario selecciona el producto {string}")
    public void elUsuarioSeleccionaElProducto(String productName) {
        new HomePage().seleccionarProducto(productName);

        ProductPage productPage = new ProductPage();
        selectedProductName = productPage.getProductName();
        selectedProductPrice = productPage.getProductPrice();

        System.out.println("Producto seleccionado: " + selectedProductName);
        System.out.println("Precio seleccionado: " + selectedProductPrice);
    }

    @And("agrega el producto al carrito")
    public void elUsuarioAgregaElProductoAlCarrito() {
        new ProductPage().addProductToCart();
    }

    @And("confirma el mensaje de producto agregado")
    public void elUsuarioAceptaElMensajeDeConfirmacion() {
        new ProductPage().acceptMessageConfirmation();
    }

    @And("accede al carrito de compras")
    public void elUsuarioIngresaAlCarrito() {
        new HeaderPage().goToCart();
    }

    @Then("valido que el producto seleccionado se visualice en el carrito")
    public void validoQueElProductoSeleccionadoSeVisualiceEnElCarrito() {

        Assertions.assertTrue(
                new CartPage().productVisibleInCart(selectedProductName),
                "El producto " + selectedProductName + " no se visualiza en el carrito"
        );
    }

    @And("valido que el precio del producto seleccionado corresponda en el carrito")
    public void validoQueElPrecioDelProductoSeleccionadoCorrespondaEnElCarrito() {

        int precioActualCarrito = new CartPage().getProductPriceInCart(selectedProductName);

        System.out.println("Producto seleccionado: " + selectedProductName);
        System.out.println("Precio capturado en detalle: " + selectedProductPrice);
        System.out.println("Precio visualizado en carrito: " + precioActualCarrito);

        Assertions.assertEquals(
                selectedProductPrice,
                precioActualCarrito,
                "El precio del producto " + selectedProductName +
                        " no corresponde. Se esperaba " + selectedProductPrice +
                        ", pero en el carrito se visualizó " + precioActualCarrito
        );
    }

    @And("el usuario hace clic en el boton Place Order")
    public void elUsuarioHaceClicEnElBotonPlaceOrder() {
        new CartPage().clickPlaceOrder();
    }

    @And("completa el formulario de compra con los siguientes datos")
    public void completaElFormularioDeCompraConLosSiguientesDatos(DataTable dataTable) {

        Map<String, String> purchaseData = dataTable.asMap(String.class, String.class);

        purchaseName = purchaseData.get("name");
        purchaseCountry = purchaseData.get("country");
        purchaseCity = purchaseData.get("city");
        purchaseCreditCard = purchaseData.get("creditCard");
        purchaseMonth = purchaseData.get("month");
        purchaseYear = purchaseData.get("year");

        new CartPage().fillPurchaseForm(purchaseName,purchaseCountry,purchaseCity,purchaseCreditCard,purchaseMonth,purchaseYear);
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        new CartPage().clickPurchase();
    }

    @Then("valido que se visualice el modal de compra exitosa con el mensaje {string}")
    public void validoQueSeVisualiceElModalDeCompraExitosaConElMensaje(String mensajeEsperado) {

        String mensajeActual = new CartPage().getSuccessTitle();

        Assertions.assertEquals(
                mensajeEsperado,
                mensajeActual,
                "No se visualizó el mensaje esperado en el modal de compra exitosa"
        );
    }

    @And("valido que el modal muestre el nombre y numero de tarjeta ingresados")
    public void validoQueElModalMuestreElNombreYNumeroDeTarjetaIngresados() {

        String modalDetails = new CartPage().getSuccessDetails();

        System.out.println("Detalle modal compra: " + modalDetails);

        Assertions.assertTrue(
                modalDetails.contains("Name: " + purchaseName),
                "El nombre mostrado en el modal no corresponde al nombre ingresado"
        );

        Assertions.assertTrue(
                modalDetails.contains("Card Number: " + purchaseCreditCard),
                "La tarjeta mostrada en el modal no corresponde a la tarjeta ingresada"
        );
    }

}
