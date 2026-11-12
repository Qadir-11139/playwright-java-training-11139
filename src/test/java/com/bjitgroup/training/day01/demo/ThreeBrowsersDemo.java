package com.bjitgroup.training.day01.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Day 01 - Live demo 2: the same check in Chromium, Firefox and WebKit, headless.
 *
 * <p>Run it: {@code mvn test -Dtest=ThreeBrowsersDemo}
 *
 * <p>What to point out: one API for three engines, no driver downloads, and headless
 * runs are faster because nothing is drawn on screen. The console shows each
 * browser's real version, which is pinned to the Playwright version in the pom.
 */
public class ThreeBrowsersDemo {

    @Test
    public void sameTestInThreeBrowsers() {
        try (Playwright playwright = Playwright.create()) {
            List<BrowserType> browserTypes = List.of(
                    playwright.chromium(), playwright.firefox(), playwright.webkit());

            for (BrowserType browserType : browserTypes) {
                Browser browser = browserType.launch(
                        new BrowserType.LaunchOptions().setHeadless(true));
                Page page = browser.newPage(); // shortcut: creates its own context

                page.navigate("https://www.saucedemo.com");
                assertThat(page).hasTitle("Swag Labs");

                System.out.printf("%-8s %-16s -> %s%n",
                        browserType.name(), browser.version(), page.title());
                browser.close();
            }
        }
    }
}
