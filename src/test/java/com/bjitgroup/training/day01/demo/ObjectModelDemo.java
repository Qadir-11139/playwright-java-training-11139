package com.bjitgroup.training.day01.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Day 01 - Live demo 3: why BrowserContext matters.
 *
 * <p>One browser, two contexts. The user logs in inside context A. Context B is a
 * separate "incognito profile": it has no cookies, so opening the inventory page
 * there is refused. This is how Playwright keeps tests isolated without starting a
 * new browser for every test.
 *
 * <p>Run it: {@code mvn test -Dtest=ObjectModelDemo}
 */
public class ObjectModelDemo {

    @Test
    public void contextsDoNotShareLoginState() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(300));

            // Context A: log in
            BrowserContext contextA = browser.newContext();
            Page pageA = contextA.newPage();
            pageA.navigate("https://www.saucedemo.com");
            pageA.getByPlaceholder("Username").fill("standard_user");
            pageA.getByPlaceholder("Password").fill("secret_sauce");
            pageA.locator("#login-button").click();
            assertThat(pageA).hasURL(Pattern.compile(".*inventory\\.html"));

            // Context B: same browser, fresh profile, not logged in
            BrowserContext contextB = browser.newContext();
            Page pageB = contextB.newPage();
            pageB.navigate("https://www.saucedemo.com/inventory.html");
            assertThat(pageB.locator("[data-test='error']"))
                    .containsText("when you are logged in");

            System.out.println("Contexts in this browser: " + browser.contexts().size());
        }
    }
}
