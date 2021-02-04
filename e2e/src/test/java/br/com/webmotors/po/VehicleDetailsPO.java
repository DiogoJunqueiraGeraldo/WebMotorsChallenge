package br.com.webmotors.po;

import br.com.webmotors.util.Sleep;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class VehicleDetailsPO {

    public static final String url = "https://www.webmotors.com.br/carros/estoque?idcmpint=t1:c17:m07:webmotors:busca::verofertas";
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public VehicleDetailsPO(WebDriver _driver, WebDriverWait _wait) {
        this.driver = _driver;
        this.wait = _wait;
    }

    public String getSellerName() {
        By locator = By.xpath("//h2[@id='VehicleSellerInformationName']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    public void scrollDownToStockFromThisSellerLink() {
        // Switch to last window
        Set<String> windowHandles = driver.getWindowHandles();
        for(String handle: windowHandles) {
            driver.switchTo().window(handle);
        }

        By locator = By.xpath("//h2[text()='Compare os preços']");
        WebElement element = driver.findElement(locator);

        // Scroll Down
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        // @Todo(Diogo Junqueira Geraldo) Improve animations with wait.until
        Sleep.seconds(1);
    }

    public void clickOnStockFromThisSellerLink() {
        // @Todo(Diogo Junqueira Geraldo) Improve Click to be a black box test
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('VehicleSellerInformationStock').click();");

        // @Todo(Diogo Junqueira Geraldo) Improve loads with wait.until
        Sleep.seconds(5);
    }
}
