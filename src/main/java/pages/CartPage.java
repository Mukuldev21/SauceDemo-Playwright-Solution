package pages;

import com.microsoft.playwright.Page;
import java.util.List;

public class CartPage extends BasePage {


    // Locators
    private static final String CART_ITEM = ".cart_item";
    private static final String ITEM_NAME = ".inventory_item_name";
    private static final String ITEM_PRICE = ".inventory_item_price";
    private static final String REMOVE_BUTTON = "button[id^='remove']";
    private static final String CONTINUE_SHOPPING_BUTTON = "#continue-shopping";
    private static final String CHECKOUT_BUTTON = "#checkout";

    public CartPage(Page page) {
        super(page);
    }

    public boolean isOnCartPage() {
        return page.url().contains("cart.html");
    }

    public int getCartItemCount() {
        return page.locator(CART_ITEM).count();
    }

    public List<String> getCartItemNames() {
        return page.locator(ITEM_NAME).allTextContents();
    }

    public List<String> getCartItemPrices() {
        return page.locator(ITEM_PRICE).allTextContents();
    }

    public void removeItem(int index) {
        page.locator(REMOVE_BUTTON).nth(index).click();
    }

    public void removeItemByName(String itemName) {
        String locator = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//button[starts-with(@id, 'remove')]", itemName);
        page.locator(locator).click();
    }

    public void clickContinueShopping() {
        page.click(CONTINUE_SHOPPING_BUTTON);
    }

    public void clickCheckout() {
        page.click(CHECKOUT_BUTTON);
    }
}

