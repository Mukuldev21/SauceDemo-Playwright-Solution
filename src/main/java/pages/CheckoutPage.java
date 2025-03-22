package pages;

import com.microsoft.playwright.Page;

public class CheckoutPage extends BasePage {

    // Locators
    private static final String FIRST_NAME_INPUT = "#first-name";
    private static final String LAST_NAME_INPUT = "#last-name";
    private static final String POSTAL_CODE_INPUT = "#postal-code";
    private static final String CONTINUE_BUTTON = "#continue";
    private static final String CANCEL_BUTTON = "#cancel";
    private static final String ERROR_MESSAGE = ".error-message-container h3";
    private static final String FINISH_BUTTON = "#finish";
    private static final String BACK_HOME_BUTTON = "#back-to-products";
    private static final String CHECKOUT_COMPLETE_HEADER = ".complete-header";
    private static final String SUBTOTAL_LABEL = ".summary_subtotal_label";
    private static final String TAX_LABEL = ".summary_tax_label";
    private static final String TOTAL_LABEL = ".summary_total_label";


    public CheckoutPage(Page page) {
        super(page);
    }

    public boolean isOnCheckoutStepOne() {
        return page.url().contains("checkout-step-one.html");
    }

    public boolean isOnCheckoutStepTwo() {
        return page.url().contains("checkout-step-two.html");
    }

    public boolean isOnCheckoutComplete() {
        return page.url().contains("checkout-complete.html");
    }

    public void enterFirstName(String firstName) {
        page.fill(FIRST_NAME_INPUT, firstName);
    }

    public void enterLastName(String lastName) {
        page.fill(LAST_NAME_INPUT, lastName);
    }

    public void enterPostalCode(String postalCode) {
        page.fill(POSTAL_CODE_INPUT, postalCode);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        page.click(CONTINUE_BUTTON);
    }

    public void clickCancel() {
        page.click(CANCEL_BUTTON);
    }

    public String getErrorMessage() {
        return page.textContent(ERROR_MESSAGE);
    }

    public String getSubtotal() {
        return page.textContent(SUBTOTAL_LABEL);
    }

    public String getTax() {
        return page.textContent(TAX_LABEL);
    }

    public String getTotal() {
        return page.textContent(TOTAL_LABEL);
    }

    public void clickFinish() {
        page.click(FINISH_BUTTON);
    }

    public void clickBackHome() {
        page.click(BACK_HOME_BUTTON);
    }

    public String getCheckoutCompleteHeader() {
        return page.textContent(CHECKOUT_COMPLETE_HEADER);
    }
}
