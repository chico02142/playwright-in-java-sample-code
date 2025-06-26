package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class ASimplePlaywrightTest2 {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    Page page;

    @BeforeAll
    public static void setUpBrowser() {
        // TODO: Write your first playwright test here
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extenstions", "--disable-gpu"))
        );

        browserContext = browser.newContext();
    }

    @BeforeEach
    public void setup() {
        page = browserContext.newPage();
    }

    @AfterAll
    public static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void shouldShowThePageTitle() {
        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    void shouldSearchByKeyword() {
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();
        page.get

        int matchingSearchResults = page.locator(".card").count();
        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
