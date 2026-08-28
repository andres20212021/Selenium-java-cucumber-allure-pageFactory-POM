@regression
Feature: Finalizar compra en Demoblaze

  Como usuario de la tienda Demoblaze
  Quiero completar una compra ingresando los datos del formulario
  Para validar que al finalizar la compra se muestre el modal de confirmación con los datos ingresados

  Background:
    Given el usuario abre la pagina principal de Demoblaze

  Scenario: Validar modal de compra exitosa con los datos ingresados en el formulario
    When el usuario selecciona la categoria "Monitors"
    And el usuario selecciona el producto "Apple monitor 24"
    And agrega el producto al carrito
    And confirma el mensaje de producto agregado
    And accede al carrito de compras
    And el usuario hace clic en el boton Place Order
    And completa el formulario de compra con los siguientes datos
      | name        | ANDRES ALEX HERNANDEZ HERNANDEZ |
      | country     | Chile                            |
      | city        | Santiago                         |
      | creditCard  | 333333                           |
      | month       | 9                                |
      | year        | 2026                             |
    And confirma la compra
    Then valido que se visualice el modal de compra exitosa con el mensaje "Thank you for your purchase!"
    And valido que el modal muestre el nombre y numero de tarjeta ingresados
