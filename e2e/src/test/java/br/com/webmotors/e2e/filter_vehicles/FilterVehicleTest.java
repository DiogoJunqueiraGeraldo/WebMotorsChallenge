package br.com.webmotors.e2e.filter_vehicles;

import br.com.webmotors.po.SearchPagePO;
import static org.junit.Assert.*;

import br.com.webmotors.util.Sleep;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class FilterVehicleTest {
    private static final int TIMEOUT_IN_SECONDS = 60;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        actions = new Actions(driver);
    }

    @Test
    public void assertFilterVehiclesIsFilteringVehicleResultsList() {
        String brand = "Honda";
        String model = "City";

        SearchPagePO searchPagePO = new SearchPagePO(driver, wait, actions);
        searchPagePO.navigateToPage();
        searchPagePO.acceptCoockies();
        searchPagePO.clickOnBrandButton(brand);

        assertTrue(searchPagePO.assertResultHeader(brand));
        assertTrue(searchPagePO.assertResultFilterPath(new String[] { "Home", "Carros", brand}));
        assertTrue(searchPagePO.assertResultFilterParamElement(brand));

        searchPagePO.clickOnAllTheModelsButton();
        searchPagePO.clickOnSearchModelTextField();
        searchPagePO.typeIntoSearchModelTextField(model);
        searchPagePO.clickOnModelItemList(model);

        assertTrue(searchPagePO.assertResultHeader(model));
        assertTrue(searchPagePO.assertResultFilterPath(new String[] { "Home", "Carros", brand, model}));
        assertTrue(searchPagePO.assertResultFilterParamElement(String.format("%s %s", brand, model)));
        assertTrue(searchPagePO.assertVehiclesListAreAllFromBrandAndModelFilter(brand, model));
    }

    @AfterClass
    public static void cleanup() {
        driver.close();
        driver.quit();
    }
}
