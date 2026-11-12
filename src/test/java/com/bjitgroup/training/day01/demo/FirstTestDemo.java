package com.bjitgroup.training.day01.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Day 01 - Live demo 1: the first Playwright test (same code as the "Your first test" slide).
 *
 * <p>Run it: {@code mvn test -Dtest=FirstTestDemo}
 *
 * <p>What to point out while demoing:
 * <ul>
 *   <li>The object chain Playwright -> Browser -> BrowserContext -> Page.</li>
 *   <li>try-with-resources closes Playwright, which closes every browser it started.</li>
 *   <li>No waits: click() waits for the link to be visible, enabled and stable;
 *       assertThat(page).hasURL(...) retries until the URL matches or 5 seconds pass.</li>
 * </ul>Set-Location D:\1automation-training\20-sessions\playwright-java-training
git remote -v
git pull
 */
public class FirstTestDemo {

    @Test
    public void getStartedLinkOpensTheIntroPage() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://playwright.dev");
            page.getByRole(AriaRole.LINK,
                    new Page.GetByRoleOptions().setName("Get started")).click();

            assertThat(page).hasURL(Pattern.compile(".*intro"));
        } // try-with-resources closes the browser
    }
}
