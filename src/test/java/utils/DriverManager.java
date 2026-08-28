package utils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
public class DriverManager {
    // Esta clase se encarga de guardar, entregar, cerrar y limpiar el WebDriver.
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverManager() {
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
    public static void setWindowSize(int width, int height) {
        getDriver().manage().window().setSize(
                new Dimension(width, height)
        );
    }
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
