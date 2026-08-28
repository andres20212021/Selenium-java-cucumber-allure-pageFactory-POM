@regression
Feature: Filtrado de productos por categorias en Demoblaze

  Como usuario de la tienda Demoblaze
  Quiero filtrar los productos por categoria
  Para visualizar solamente los productos correspondientes a la categoria seleccionada

  Background:
    Given el usuario abre la pagina principal de Demoblaze

  Scenario Outline: Validar cantidad de productos mostrados por categoria
    When el usuario selecciona la categoria "<categoria>"
    Then valido que se visualicen <cantidad> productos de la categoria "<categoria>"

    Examples:
      | categoria | cantidad |
      | Phones    | 7        |
      | Laptops   | 6        |
      | Monitors  | 2        |
