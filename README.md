# Automatización Selenium Java BDD Web — PageFactory

Framework de automatización web desarrollado con **Java**, **Selenium WebDriver (PageFactory)**, **Cucumber BDD**, **JUnit 5**, **Maven** y **Allure Report**.

El objetivo de este proyecto es automatizar pruebas funcionales sobre la página **Demoblaze**, aplicando buenas prácticas de automatización,
patrón **Page Object Model implementado con PageFactory** (`@FindBy` + `PageFactory.initElements`), separación de responsabilidades, reportes visuales y evidencia automática cuando una prueba falla.

---

## Tecnologías utilizadas

| Tecnología         | Uso                                          |
|--------------------|----------------------------------------------|
| Java 21            | Lenguaje principal del proyecto              |
| Maven              | Gestión de dependencias y ejecución          |
| Selenium WebDriver | Automatización del navegador (PageFactory)   |
| Cucumber BDD       | Definición de escenarios en lenguaje Gherkin |
| JUnit 5            | Motor de ejecución de pruebas                |
| Allure Report      | Generación de reportes visuales              |
| IntelliJ IDEA      | IDE utilizado para desarrollo                |
| Git / GitHub       | Control de versiones y repositorio remoto    |

## Estructura del proyecto

```text
src/test/java
├── hooks
│   └── Hooks.java              # @Before crea el WebDriver, @After adjunta screenshot en fallos y lo cierra
├── pages
│   ├── BasePage.java           # Acciones comunes con espera explícita (click, write, getText, alert, etc.)
│   ├── HomePage.java
│   ├── HeaderPage.java
│   ├── ProductPage.java
│   └── CartPage.java
├── runners
│   └── RunnerTest.java         # Suite JUnit 5 que ejecuta Cucumber + plugin de Allure
├── stepdefinitions
│   ├── Home.java
│   └── CartSteps.java
└── utils
    └── DriverManager.java      # ThreadLocal<WebDriver>: guarda, entrega y cierra el driver

src/test/resources/features
├── addProductToCart.feature
├── filterByCategory.feature
└── purchase_order.feature
```

## Ejecución local del proyecto

Para ejecutar las pruebas automatizadas de forma local, primero se debe clonar el repositorio y abrir el proyecto en un IDE como **IntelliJ IDEA**.

### Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
```

Ingresar a la carpeta del proyecto:

```bash
cd automatizacion_java_selenium_web_pagefactory
```

Para ejecutar todos los escenarios configurados en el framework:

```bash
mvn clean test
```

Visualizar el reporte Allure localmente:

```bash
mvn allure:serve
```

Este comando genera y levanta automáticamente el reporte Allure en un servidor local, abriéndolo en el navegador.

## Ejecución de pruebas de regresión en GitHub Actions

El proyecto cuenta con un pipeline configurado en **GitHub Actions** para ejecutar automáticamente las pruebas de regresión del framework.

Las pruebas de regresión se identifican mediante el tag de Cucumber:

```gherkin
@regression
```

### Ejecución programada

El pipeline también tiene preparada una configuración para ejecución programada todos los domingos a las 12:00 del día, pero actualmente se encuentra comentada en el archivo YAML:

```yaml
# schedule:
#   - cron: "0 12 * * 0"
```

Para activar esta ejecución programada, se deben quitar los comentarios de esas líneas en `.github/workflows/main.yml`.

Las pruebas de regresión se ejecutan automáticamente en las siguientes condiciones:

| Condición | Descripción |
|----------|-------------|
| Push a `main` | Se ejecutan cuando se sube un cambio directamente a la rama `main`. |
| Pull Request hacia `main` | Se ejecutan cuando se crea o actualiza una Pull Request apuntando a la rama `main`. |
| Ejecución manual | Se pueden ejecutar manualmente desde la pestaña **Actions** de GitHub. |

### Ejecución manual con un clic

Gracias a `workflow_dispatch` en el archivo YAML, el pipeline también se puede lanzar manualmente sin necesidad de hacer push ni abrir una Pull Request:

1. Ingresar al repositorio en GitHub.
2. Ir a la pestaña **Actions**.
3. En el menú lateral izquierdo, seleccionar el workflow **Web Automation Tests**.
4. Hacer clic en el botón **Run workflow**.
5. Confirmar haciendo clic nuevamente en **Run workflow**.

Esto ejecuta el pipeline sobre la rama seleccionada (por defecto `main`) sin necesidad de ningún cambio en el código.

## Configuración de GitHub Pages para publicar Allure Report

El proyecto está preparado para publicar automáticamente el reporte de Allure en **GitHub Pages** usando GitHub Actions.

Para que funcione correctamente, después de subir el proyecto al repositorio se debe activar GitHub Pages con la fuente **GitHub Actions**:

1. Ingresar al repositorio en GitHub.
2. Ir a la pestaña **Settings**.
3. En el menú lateral izquierdo, entrar a **Pages**.
4. En la sección **Build and deployment**, buscar la opción **Source**.
5. Seleccionar **GitHub Actions**.

## Sitio bajo prueba

El sitio utilizado para las pruebas automatizadas es:

```text
https://www.demoblaze.com/index.html
```
