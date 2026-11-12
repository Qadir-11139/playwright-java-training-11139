package com.bjitgroup.training.day01.lab;

import org.testng.SkipException;
import org.testng.annotations.Test;

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
        throw new SkipException("Lab 01 not started: delete this line and complete the TODOs.");

        // TODO 1: Read two system properties:
        //         browser  -> default "chromium"
        //         headless -> default "false" (use Boolean.parseBoolean)

        // TODO 2: Create Playwright inside try-with-resources:
        //         try (Playwright playwright = Playwright.create()) { ... }

        // TODO 3: Pick the BrowserType from the browser name:
        //         "chromium" -> playwright.chromium()
        //         "firefox"  -> playwright.firefox()
        //         "webkit"   -> playwright.webkit()
        //         anything else -> throw new IllegalArgumentException(...)

        // TODO 4: Launch it with new BrowserType.LaunchOptions().setHeadless(headless)

        // TODO 5: Create a BrowserContext, then a Page from that context

        // TODO 6: Navigate to https://www.saucedemo.com

        // TODO 7: assertThat(page).hasTitle("Swag Labs");
        //         import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

        // BONUS: also assert that the Username field is visible:
        //         assertThat(page.getByPlaceholder("Username")).isVisible();
    }
}
