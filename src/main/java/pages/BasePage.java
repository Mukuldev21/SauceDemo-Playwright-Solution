package pages;

import com.microsoft.playwright.Page;
import config.Config;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void navigateToBaseUrl() {
        navigateTo(Config.BASE_URL);
    }

    public String getPageTitle() {
        return page.title();
    }

    public void waitForLoad() {
        page.waitForLoadState();
    }

    public void closePage() {
        page.close();
    }
}
