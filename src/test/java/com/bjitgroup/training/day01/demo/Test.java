package com.bjitgroup.training.day01.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/** Standalone Playwright test that checks the Swag Labs page title. */
public class Test {

    @org.testng.annotations.Test
    public void saucedemoDisplaysItsTitle() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            page.navigate("https://www.saucedemo.com");

            assertThat(page).hasTitle("Swag Labs");
        }
    }
}
