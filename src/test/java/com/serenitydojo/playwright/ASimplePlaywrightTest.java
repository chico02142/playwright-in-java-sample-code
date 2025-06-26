package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@UsePlaywright(ASimplePlaywrightTest.MyOptions.class)
public class ASimplePlaywrightTest {

//    Playwright playwright;
//    Browser browser;
//    Page page;
//
//    @BeforeEach
//    void setup() {
//        // TODO: Write your first playwright test here
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(
//                new BrowserType.LaunchOptions().setHeadless(false)
//                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extenstions", "--disable-gpu"))
//        );
//        page = browser.newPage();
//    }
//
//    @AfterEach
//    void teardown() {
//        browser.close();
//        playwright.close();
//    }

    public static class MyOptions implements OptionsFactory {

        @Override
        public Options getOptions() {
            return new Options()
                    .setHeadless(false)
                    .setLaunchOptions(
                            new BrowserType.LaunchOptions()
                                    .setArgs(Arrays.asList("--no-sandbox", "--disable-extenstions", "--disable-gpu"))
                    );
        }
    }
    @Test
    void shouldShowThePageTitle(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    void shouldSearchByKeyword(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();

        int matchingSearchResults = page.locator(".card").count();
        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
