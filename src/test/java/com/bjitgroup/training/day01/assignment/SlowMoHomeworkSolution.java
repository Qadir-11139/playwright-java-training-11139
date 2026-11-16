package com.bjitgroup.training.day01.assignment;

import com.microsoft.playwright.Browser;

import com.microsoft.playwright.BrowserType;

import com.microsoft.playwright.Page;

import com.microsoft.playwright.Playwright;

import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**

 * Day 01 - Homework solution: slow motion.

 *

 * <p>setSlowMo(500) adds a 500 ms pause before every Playwright action, so you can

 * watch each step. Use it only to watch or debug, never in a real suite: it makes

 * every test slower and does not fix timing problems.

 *

 * <p>Run (headed, so you can watch): {@code mvn test -Dtest=SlowMoHomeworkSolution}

 */

public class SlowMoHomeworkSolution {

    @Test

    public void watchTheLoginStepByStep() {

        // TODO: Task 1 · Slow motion and login

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()

                    .setHeadless(false)

                    .setSlowMo(500));

            Page page = browser.newContext().newPage();

            page.navigate("https://www.saucedemo.com");

            page.getByPlaceholder("Username").fill("standard_user");

            page.getByPlaceholder("Password").fill("secret_sauce");

            page.locator("#login-button").click();

            assertThat(page).hasURL(Pattern.compile(".*inventory\\.html"));

        }

    }

}

