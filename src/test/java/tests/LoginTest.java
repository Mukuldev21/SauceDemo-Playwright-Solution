package tests;

import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
        loginPage.navigateToLoginPage();
    }

    @Test(description = "Verify successful login with standard user")
    public void testSuccessfulLogin() {
        loginPage.loginAsStandardUser();
        Assert.assertTrue(inventoryPage.isOnInventoryPage(), "User should be redirected to inventory page after login");
    }

    @Test(description = "Verify login with locked out user")
    public void testLockedOutUser() {
        loginPage.loginAsLockedOutUser();
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Error message should indicate that the user is locked out");
    }

    @Test(description = "Verify login with invalid credentials")
    public void testInvalidCredentials() {
        loginPage.login("invalid_user", "invalid_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message should indicate invalid credentials");
    }

    @Test(description = "Verify login with empty username")
    public void testEmptyUsername() {
        loginPage.login("", Config.PASSWORD);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Error message should indicate username is required");
    }

    @Test(description = "Verify login with empty password")
    public void testEmptyPassword() {
        loginPage.login(Config.STANDARD_USER, "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"),
                "Error message should indicate password is required");
    }

    @Test(description = "Verify login with invalid username")
    public void testInvalidUsername() {
        loginPage.login("invalid_user", Config.PASSWORD);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message should indicate invalid credentials");
    }

    @Test(description = "Verify login with performance glitch user")
    public void testPerformanceGlitchUser() {
        loginPage.loginAsPerformanceGlitchUser();
        Assert.assertTrue(inventoryPage.isOnInventoryPage(), "User should be redirected to inventory page after login");
    }
}
