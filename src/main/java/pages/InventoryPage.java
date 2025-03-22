package pages;

import com.microsoft.playwright.Page;
import java.util.List;


public class InventoryPage extends BasePage{


    // Locators
    private static final String INVENTORY_ITEM = ".inventory_item";
    private static final String ITEM_NAME = ".inventory_item_name";
    private static final String ITEM_PRICE = ".inventory_item_price";
    private static final String ADD_TO_CART_BUTTON = "button[id^='add-to-cart']";
    private static final String REMOVE_BUTTON = "button[id^='remove']";
    private static final String SHOPPING_CART_BADGE = ".shopping_cart_badge";
    private static final String SHOPPING_CART_LINK = ".shopping_cart_link";
    private static final String SORT_DROPDOWN = ".product_sort_container";

    public InventoryPage(Page page) {
        super(page);
    }

    public boolean isOnInventoryPage() {
        return page.url().contains("inventory.html");
    }

    public int getItemCount() {
        return page.locator(INVENTORY_ITEM).count();
    }
    public void addItemToCart(int index) {
        page.locator(ADD_TO_CART_BUTTON).nth(index).click();
    }

    public void addItemToCartByName(String itemName) {
        String locator = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[starts-with(@id, 'add-to-cart')]", itemName);
        page.locator(locator).click();
    }

    public void removeItemFromCart(int index) {
        page.locator(REMOVE_BUTTON).nth(index).click();
    }

    public int getCartItemCount() {
        if (page.locator(SHOPPING_CART_BADGE).isVisible()) {
            return Integer.parseInt(page.locator(SHOPPING_CART_BADGE).textContent());
        }
        return 0;
    }

    public void clickOnCart() {
        page.click(SHOPPING_CART_LINK);
    }

    public List<String> getAllItemNames() {
        return page.locator(ITEM_NAME).allTextContents();
    }

    public List<String> getAllItemPrices() {
        return page.locator(ITEM_PRICE).allTextContents();
    }

    public void sortBy(String option) {
        page.selectOption(SORT_DROPDOWN, option);
    }

    public void sortByNameAZ() {
        sortBy("az");
    }

    public void sortByNameZA() {
        sortBy("za");
    }

    public void sortByPriceLowToHigh() {
        sortBy("lohi");
    }

    public void sortByPriceHighToLow() {
        sortBy("hilo");
    }
}

