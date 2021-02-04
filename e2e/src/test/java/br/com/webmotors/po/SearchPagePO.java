package br.com.webmotors.po;

import br.com.webmotors.util.Sleep;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SearchPagePO {
    public static final String url = "https://www.webmotors.com.br/carros/estoque?idcmpint=t1:c17:m07:webmotors:busca::verofertas";
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPagePO(WebDriver _driver, WebDriverWait _wait) {
        this.driver = _driver;
        this.wait = _wait;
    }

    public void navigateToPage() {
        this.driver.get(SearchPagePO.url);
    }

    public void acceptCoockies() {
        By locator = By.xpath("//button[text()='OK']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);

        element.click();
    }

    public void scrollDownToSellerFilter() {
        // Scroll Down to a bottom element
        By locator = By.xpath("//div[text()='Opcionais']");
        WebElement element = driver.findElement(locator);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        // @Todo(Diogo Junqueira Geraldo) Improve animations with wait.until
        Sleep.seconds(1);
    }

    public void clickOnBrandButton(String brand) {
        By locator = By.xpath(String.format("//a[@title='%s']", brand.toLowerCase()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement element = driver.findElement(locator);

        element.click();

        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(5);
    }

    public void clickOnAllTheModelsButton() {
        By locator = By.xpath("//div[text()='Todos os modelos']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement element = driver.findElement(locator);

        element.click();
        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(1);
    }

    public void clickOnSearchModelTextField() {
        By locator = By.xpath("//input[@type='text' and @class=' Form__field__noerror' and @tabindex=-1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement element = driver.findElement(locator);

        element.click();
        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(1);
    }

    public void clickOnModelItemList(String model) {
        By locator = By.xpath(String.format("//a[text()='%s']", model.toUpperCase()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement element = driver.findElement(locator);

        element.click();

        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(5);
    }


    public void clickOnFilterBySellers() {
        By locator = By.xpath("//label[text()='Loja']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement element = driver.findElement(locator);
        element.click();

        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(5);
    }

    public void clickOnFirstResult() {
        By locator = By.xpath("//div[@class='sc-TOsTZ cgVgKJ']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);

        element.click();

        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(5);
    }

    public void typeIntoSearchModelTextField(String model) {
        By locator = By.xpath("//input[@type='text' and @class=' Form__field__noerror' and @tabindex=-1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);

        element.sendKeys(model);
        // @Todo(Diogo Junqueira Geraldo): Talk to web developer and understand how to wait for the load.
        Sleep.seconds(1);
    }

    public boolean assertResultHeader(String brand) {
        By locator = By.xpath("//h1[@class='title-search']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        String headerText = element.getText();
        return headerText.contains(brand);
    }

    public boolean assertResultFilterPath(String[] filterPath) {
        By locator = By.xpath("//li[@itemprop='itemListElement']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        List<WebElement> elements = driver.findElements(locator);

        int elementsLength = elements.size();
        if(filterPath.length != elementsLength)
            return  false;

        for(int i = 0; i < elementsLength; i++) {
            WebElement element = elements.get(i);

            if(!filterPath[i].equals(element.getText())){
                return false;
            }
        }

        return true;
    }

    public boolean assertResultFilterParamElement(String item) {
        By locator = By.xpath(String.format("//a[@id='%s']", item.toLowerCase().replace(' ', '-')));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);

        return element.getText().equals(item.toUpperCase());
    }


    public boolean assertVehiclesListAreAllFromBrandAndModelFilter(String brand, String model) {
        By locator = By.xpath("//h2[@class='sc-dNLxif jJaNms']");
        List<WebElement> elements = driver.findElements(locator);

        String expectedText = String.format("%s %s", brand, model).toUpperCase();
        boolean allElementsMatch = true;

        for(WebElement element: elements) {
            if(!element.getText().equals(expectedText)) {
                allElementsMatch = false;
            }
        }

        return allElementsMatch;
    }
}
