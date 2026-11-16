package com.bjitgroup.training.day01.lab;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Day 01 - Hands-on lab (25 min): Hello, Playwright.
 *
 * <p>Goal: open https://www.saucedemo.com and check that the page title is "Swag Labs",
 * in Chromium, Firefox and WebKit.
 *
 * <p>How to work:
 * <ol>
 *   <li>Delete the {@code throw new SkipException(...)} line below.</li>
 *   <li>Complete the TODOs in order. Add the imports IntelliJ suggests (Alt+Enter).</li>
 *   <li>Run: {@code mvn test -Dtest=HelloPlaywrightLab}</li>
 *   <li>Then headless: {@code mvn test -Dtest=HelloPlaywrightLab -Dheadless=true}</li>
 *   <li>Then other browsers: add {@code -Dbrowser=firefox} or {@code -Dbrowser=webkit}</li>
 * </ol>
 *
 * <p>Done when: the test passes in all three browsers.
 * Stuck? Compare with {@code day01.solution.HelloPlaywrightSolution} - after you have tried.
 */
public class HelloPlaywrightLab {

    @Test
    public void saucedemoTitleIsSwagLabs() {
        String browserName = System.getProperty("browser", "chromium");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = switch (browserName.toLowerCase()) {
                case "chromium" -> playwright.chromium();
                case "firefox" -> playwright.firefox();
                case "webkit" -> playwright.webkit();
                default -> throw new IllegalArgumentException(
                        "Unknown browser '" + browserName + "'. Use chromium, firefox or webkit.");
            };
            Browser browser = browserType.launch(
                    new BrowserType.LaunchOptions().setHeadless(headless));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://www.saucedemo.com");

            assertThat(page).hasTitle("Swag Labs");
            assertThat(page.getByPlaceholder("Username")).isVisible();
        }
    }
}
