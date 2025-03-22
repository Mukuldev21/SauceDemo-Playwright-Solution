package tests;

import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class CartTest extends BaseTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void initPages() {
        LoginPage loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
        cartPage = new CartPage(page);

        loginPage.navigateToLoginPage();
        loginPage.loginAsStandardUser();
    }

    @Test(description = "Verify adding item to cart and viewing cart")
    public void testAddItemAndViewCart() {
        // Add first item to cart
        inventoryPage.addItemToCart(0);
        String itemName = inventoryPage.getAllItemNames().getFirst();

        // Go to cart
        inventoryPage.clickOnCart();
        Assert.assertTrue(cartPage.isOnCartPage(), "User should be redirected to cart page");

        // Verify item is in cart
        List<String> cartItemNames = cartPage.getCartItemNames();
        Assert.assertEquals(cartItemNames.size(), 1, "Cart should have 1 item");
        Assert.assertEquals(cartItemNames.getFirst(), itemName, "The item in cart should match the added item");
    }

    @Test(description = "Verify adding multiple items to cart")
    public void testAddMultipleItemsToCart() {
        // Add multiple items to cart
        inventoryPage.addItemToCart(0);
        inventoryPage.addItemToCart(1);

        // Get names of the added items
        List<String> addedItemNames = inventoryPage.getAllItemNames().subList(0, 2);

        // Go to cart
        inventoryPage.clickOnCart();

        // Verify items in cart
        List<String> cartItemNames = cartPage.getCartItemNames();
        Assert.assertEquals(cartItemNames.size(), 2, "Cart should have 2 items");
        Assert.assertTrue(cartItemNames.containsAll(addedItemNames), "The items in cart should match the added items");
    }

    @Test(description = "Verify removing item from cart")
    public void testRemoveItemFromCart() {
        // Add first item to cart
        inventoryPage.addItemToCart(0);

        // Go to cart
        inventoryPage.clickOnCart();

        // Verify item is in cart
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should have 1 item");

        // Remove item from cart
        cartPage.removeItem(0);

        // Verify item is removed
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after removing the item");
    }

    @Test(description = "Verify continue shopping button")
    public void testContinueShopping() {
        // Go to cart
        inventoryPage.clickOnCart();

        // Click continue shopping
        cartPage.clickContinueShopping();

        // Verify redirected back to inventory
        Assert.assertTrue(inventoryPage.isOnInventoryPage(), "User should be redirected back to inventory page");
    }

}
