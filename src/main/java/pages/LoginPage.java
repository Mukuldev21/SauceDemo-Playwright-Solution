package pages;

import com.microsoft.playwright.Page;
import config.Config;
public class LoginPage extends BasePage {

    // Locators
    private static final String USERNAME_INPUT = "#user-name";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "#login-button";
    private static final String ERROR_MESSAGE = ".error-message-container h3";

    public LoginPage(Page page) {
        super(page);
    }

    public void navigateToLoginPage() {
        navigateToBaseUrl();
    }

    public void enterUserName(String userName) {
        page.fill(USERNAME_INPUT, userName);
    }
    public void enterPassword(String password) {
        page.fill(PASSWORD_INPUT, password);
    }
    public void clickLoginButton() {
        page.click(LOGIN_BUTTON);
    }
    public String getErrorMessage() {
        return page.textContent(ERROR_MESSAGE);
    }

    public void login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
    }

    public void loginAsStandardUser() {
        login(Config.STANDARD_USER,Config.PASSWORD);
    }

    public void loginAsLockedOutUser() {
        login(Config.LOCKED_OUT_USER,Config.PASSWORD);
    }

    public void loginAsProblemUser() {
        login(Config.PROBLEM_USER,Config.PASSWORD);
    }

    public void loginAsPerformanceGlitchUser() {
        login(Config.PERFORMANCE_GLITCH_USER,Config.PASSWORD);
    }

}
