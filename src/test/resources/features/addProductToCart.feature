@regression
Feature: Agregar producto al carrito en Demoblaze

  Como usuario de la tienda Demoblaze
  Quiero agregar un producto al carrito
  Para validar que el producto seleccionado quede registrado correctamente con su precio

  Background:
    Given el usuario abre la pagina principal de Demoblaze

  Scenario: Agregar producto Phones al carrito y validar precio
    When el usuario selecciona la categoria "Phones"
    And el usuario selecciona el producto "Samsung galaxy s6"
    And agrega el producto al carrito
    And confirma el mensaje de producto agregado
    And accede al carrito de compras
    Then valido que el producto seleccionado se visualice en el carrito
    And valido que el precio del producto seleccionado corresponda en el carrito


  Scenario: Agregar producto Laptops al carrito y validar precio
    When el usuario selecciona la categoria "Laptops"
    And el usuario selecciona el producto "2017 Dell 15.6 Inch"
    And agrega el producto al carrito
    And confirma el mensaje de producto agregado
    And accede al carrito de compras
    Then valido que el producto seleccionado se visualice en el carrito
    And valido que el precio del producto seleccionado corresponda en el carrito
