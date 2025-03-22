package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

public class InventoryTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
        loginPage.navigateToLoginPage();
    }

    @Test(description= "Verify inventory items are displayed")
    public void testInventoryItemsDisplayed() {
        int itemCount = inventoryPage.getItemCount();
        Assert.assertEquals(itemCount, 6, "There should be 6 items in the inventory");
    }

    @Test(description= "Verify adding item to cart")
    public void testAddItemsToCart() {
        inventoryPage.addItemToCart(0);
        Assert.assertEquals(inventoryPage.getCartItemCount(), 1,"Cart should have 1 item after adding");

    }

    @Test(description= "Verify removing item from cart")
    public void testRemoveItemsFromCart() {
        inventoryPage.addItemToCart(0);
        Assert.assertEquals(inventoryPage.getCartItemCount(), 1,"Cart should have 1 item after adding");

        inventoryPage.removeItemFromCart(0);
        Assert.assertEquals(inventoryPage.getCartItemCount(), 0,"Cart should have 0 item after removing");
    }

    @Test(description= "Verify adding multiple items to cart")
    public void testAddMultipleItemsToCart() {
        inventoryPage.addItemToCart(0);
        inventoryPage.addItemToCart(1);
        inventoryPage.addItemToCart(2);
        Assert.assertEquals(inventoryPage.getCartItemCount(), 3,"Cart should have 3 items after adding");
    }

    @Test(description= "Verify sorting items by name(A to Z)")
    public void testSortByNameAZ() {
        inventoryPage.sortByNameAZ();
        List<String> itemNames = inventoryPage.getAllItemNames();

        for (int i = 0; i < itemNames.size() - 1; i++) {
            Assert.assertTrue(itemNames.get(i).compareTo(itemNames.get(i + 1)) <= 0,
                    "Items should be sorted alphabetically (A to Z)");
        }

    }

    @Test(description= "Verify sorting items by name(Z to A)")
    public void testSortByNameZA() {
        inventoryPage.sortByNameZA();
        List<String> itemNames = inventoryPage.getAllItemNames();
        for (int i = 0; i < itemNames.size() - 1; i++) {
            Assert.assertTrue(itemNames.get(i).compareTo(itemNames.get(i + 1)) >= 0,
                    "Items should be sorted alphabetically (Z to A)");
        }
    }

    @Test(description = "Verify sorting by price (low to high)")
    public void testSortByPriceLowToHigh() {
        inventoryPage.sortByPriceLowToHigh();
        List<String> itemPrices = inventoryPage.getAllItemPrices();

        for (int i = 0; i < itemPrices.size() - 1; i++) {
            double price1 = Double.parseDouble(itemPrices.get(i).replace("$", ""));
            double price2 = Double.parseDouble(itemPrices.get(i + 1).replace("$", ""));
            Assert.assertTrue(price1 <= price2, "Items should be sorted by price (low to high)");
        }
    }

    @Test(description = "Verify sorting by price (high to low)")
    public void testSortByPriceHighToLow() {
        inventoryPage.sortByPriceHighToLow();
        List<String> itemPrices = inventoryPage.getAllItemPrices();

        for (int i = 0; i < itemPrices.size() - 1; i++) {
            double price1 = Double.parseDouble(itemPrices.get(i).replace("$", ""));
            double price2 = Double.parseDouble(itemPrices.get(i + 1).replace("$", ""));
            Assert.assertTrue(price1 >= price2, "Items should be sorted by price (high to low)");
        }
    }
}
