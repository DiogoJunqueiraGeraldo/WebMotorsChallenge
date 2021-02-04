package br.com.webmotors.e2e.list_store_stock;

import br.com.webmotors.po.SearchPagePO;
import br.com.webmotors.po.VehicleDetailsPO;
import br.com.webmotors.util.Sleep;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListStoreStockTest {
    private static final int TIMEOUT_IN_SECONDS = 60;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
    }

    @Test
    public void assertFilterByStoreIsFilteringResultList() {
        SearchPagePO searchPagePO = new SearchPagePO(driver, wait);
        VehicleDetailsPO vehicleDetailsPO = new VehicleDetailsPO(driver, wait);

        searchPagePO.navigateToPage();
        searchPagePO.acceptCoockies();
        searchPagePO.scrollDownToSellerFilter();

        searchPagePO.clickOnFilterBySellers();
        searchPagePO.clickOnFirstResult();

        vehicleDetailsPO.scrollDownToStockFromThisSellerLink();
        String sellerName = vehicleDetailsPO.getSellerName();
        vehicleDetailsPO.clickOnStockFromThisSellerLink();

        searchPagePO.assertResultFilterParamElement(sellerName);
    }

    @AfterClass
    public static void cleanup() {
        driver.close();
        driver.quit();
    }
}
