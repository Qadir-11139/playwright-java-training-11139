package com.bjitgroup.training.day01.solution;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Day 01 - Lab solution: Hello, Playwright.
 *
 * <p>Run:
 * <pre>
 * mvn test -Dtest=HelloPlaywrightSolution                       (Chromium, headed)
 * mvn test -Dtest=HelloPlaywrightSolution -Dheadless=true       (Chromium, headless)
 * mvn test -Dtest=HelloPlaywrightSolution -Dbrowser=firefox
 * mvn test -Dtest=HelloPlaywrightSolution -Dbrowser=webkit
 * </pre>
 *
 * <p>The browserType(...) helper is the seed of Day 02's BrowserFactory.
 */
public class HelloPlaywrightSolution {

    private static final String BASE_URL = "https://www.saucedemo.com";

    @Test
    public void saucedemoTitleIsSwagLabs() {
        String browserName = System.getProperty("browser", "chromium");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        try (Playwright playwright = Playwright.create()) {
            Browser browser = browserType(playwright, browserName)
                    .launch(new BrowserType.LaunchOptions().setHeadless(headless));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate(BASE_URL);

            assertThat(page).hasTitle("Swag Labs");
            assertThat(page.getByPlaceholder("Username")).isVisible(); // bonus

            System.out.printf("%s %s, headless=%s -> \"%s\"%n",
                    browserName, browser.version(), headless, page.title());
        }
    }

    static BrowserType browserType(Playwright playwright, String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chromium" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> throw new IllegalArgumentException(
                    "Unknown browser '" + browserName + "'. Use chromium, firefox or webkit.");
        };
    }
}
