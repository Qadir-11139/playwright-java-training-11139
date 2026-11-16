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
    // It defines base URL of the application under test in the string object
    private static final String BASE_URL = "https://www.saucedemo.com";
    @Test
    public void saucedemoTitleIsSwagLabs() {

        // It gets the browser name. Uses Chromium by default.
        String browserName = System.getProperty("browser", "chromium");

        // Gets the headless setting. The browser should be visible by default.
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Creates Playwright instance and closes it automatically after the test
        try (Playwright playwright = Playwright.create()) {

            // Selects and launches the browser
            Browser browser = browserType(playwright, browserName)
                    .launch(new BrowserType.LaunchOptions().setHeadless(headless));

            // Creates a separate browser session
            BrowserContext context = browser.newContext();

            // Opens a new browser page
            Page page = context.newPage();

            // Opens the SauceDemo website
            page.navigate(BASE_URL);

            // Checks that the page title is "Swag Labs"
            assertThat(page).hasTitle("Swag Labs");

            // Checks that the username field is visible
            assertThat(page.getByPlaceholder("Username")).isVisible(); // bonus

            // Prints the test information
            System.out.printf("%s %s, headless=%s -> \"%s\"%n",
                    browserName, browser.version(), headless, page.title());
        }
    }

    static BrowserType browserType(Playwright playwright, String browserName) {

        // Matches the browser name with a Playwright browser
        return switch (browserName.toLowerCase()) {
            case "chromium" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();

            // Shows an error if the browser name is invalid.
            default -> throw new IllegalArgumentException(
                    "Unknown browser '" + browserName + "'. Use chromium, firefox or webkit.");
        };
    }
    }
