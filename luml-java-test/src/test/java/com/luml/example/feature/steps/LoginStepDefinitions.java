package com.luml.example.feature.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://example.com/login"; // 替换为实际测试URL

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // 初始化驱动
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        driver.get(baseUrl);
    }

    @When("the user enters valid username {string} and password {string}")
    public void the_user_enters_valid_username_and_password(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("the user enters invalid username {string} and password {string}")
    public void the_user_enters_invalid_username_and_password(String username, String password) {
        // 复用上面的逻辑，这里为了演示清晰单独列出，实际可合并
        the_user_enters_valid_username_and_password(username, password);
    }

    @And("clicks the login button")
    public void clicks_the_login_button() {
        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/dashboard"), "Expected URL to contain /dashboard but was: " + currentUrl);
    }

    @And("the welcome message {string} should be displayed")
    public void the_welcome_message_should_be_displayed(String expectedMessage) {
        WebElement welcomeMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome-msg")));
        assertEquals(expectedMessage, welcomeMsg.getText());
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedError) {
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-msg")));
        assertEquals(expectedError, errorMsg.getText());
    }

    // 注意：在实际项目中，建议在 @After 钩子中关闭 driver
}
