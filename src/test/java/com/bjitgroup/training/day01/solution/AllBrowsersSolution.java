package com.bjitgroup.training.day01.solution;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Day 01 - Lab "done when" check: the same test in all three browsers in one run.
 *
 * <p>Run: {@code mvn test -Dtest=AllBrowsersSolution}
 *
 * <p>TestNG runs the test method once per row of the data provider, so the report
 * shows three results: chromium, firefox and webkit. Data providers are covered
 * properly on Day 10; this is a first look.
 */
public class AllBrowsersSolution {

    @DataProvider(name = "browsers")
    public Object[][] browsers() {
        return new Object[][] {{"chromium"}, {"firefox"}, {"webkit"}};
    }

    @Test(dataProvider = "browsers")
    public void saucedemoTitleIsSwagLabs(String browserName) {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        try (Playwright playwright = Playwright.create()) {
            Browser browser = HelloPlaywrightSolution.browserType(playwright, browserName)
                    .launch(new BrowserType.LaunchOptions().setHeadless(headless));
            Page page = browser.newContext().newPage();

            page.navigate("https://www.saucedemo.com");

            assertThat(page).hasTitle("Swag Labs");
        }
    }
}
