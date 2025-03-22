package tests;

import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void initPages() {
        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);

        loginPage.navigateToLoginPage();
        loginPage.loginAsStandardUser();

        // Add an item to cart and go to cart
        inventoryPage.addItemToCart(0);
        inventoryPage.clickOnCart();
    }

    @Test(description = "Verify complete checkout process")
    public void testCompleteCheckout() {
        // Start checkout
        cartPage.clickCheckout();
        Assert.assertTrue(checkoutPage.isOnCheckoutStepOne(), "User should be redirected to checkout step one");

        // Fill checkout information
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();

        // Verify checkout step two
        Assert.assertTrue(checkoutPage.isOnCheckoutStepTwo(), "User should be redirected to checkout step two");

        // Complete checkout
        checkoutPage.clickFinish();

        // Verify checkout complete
        Assert.assertTrue(checkoutPage.isOnCheckoutComplete(), "User should be redirected to checkout complete page");
        Assert.assertTrue(checkoutPage.getCheckoutCompleteHeader().contains("THANK YOU"),
                "Checkout complete page should show a thank you message");
    }

    @Test(description = "Verify checkout with empty information")
    public void testCheckoutWithEmptyInfo() {
        // Start checkout
        cartPage.clickCheckout();

        // Try to continue without filling information
        checkoutPage.clickContinue();

        // Verify error message
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"),
                "Error message should indicate first name is required");
    }
    @Test(description = "Verify checkout with missing last name")
    public void testCheckoutWithMissingLastName() {
        // Start checkout
        cartPage.clickCheckout();

        // Fill partial information
        checkoutPage.enterFirstName("John");
        checkoutPage.clickContinue();

        // Verify error message
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"),
                "Error message should indicate last name is required");
    }
    @Test(description = "Verify checkout with missing postal code")
    public void testCheckoutWithMissingPostalCode() {
        // Start checkout
        cartPage.clickCheckout();

        // Fill partial information
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.clickContinue();

        // Verify error message
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"),
                "Error message should indicate postal code is");
    }

}
