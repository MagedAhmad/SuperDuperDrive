package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialsTest {
    @LocalServerPort
    private int port;

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SignupPage signupPage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
        this.signup();
        this.login();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    public void login() {
        driver.get("http://localhost:" + this.port + "/login");
        loginPage.loginUser("maged2", "secret");
    }

    public void signup() {
        driver.get("http://localhost:" + this.port + "/signup");
        signupPage.registerUser("maged", "ahmed", "maged2", "secret");
    }

    @Test
    @Order(1)
    public void userCanStoreAndUpdateCredentials() throws InterruptedException{
        Thread.sleep(500);
        homePage.openCredentialsTab();
        Thread.sleep(500);
        homePage.openCredentialsModal();
        Thread.sleep(500);
        homePage.saveCredential("magedraslan", "http://google.com", "secret");
        Thread.sleep(500);
        driver.get("http://localhost:" + this.port + "/");
        homePage.openCredentialsTab();
        Thread.sleep(500);
        WebElement savedCredential = driver.findElement(By.cssSelector("td.credential-username-row"));
        Assertions.assertEquals("magedraslan", savedCredential.getText());
        Thread.sleep(500);
        WebElement editButton = driver.findElement(By.cssSelector("button.edit-cred"));
        editButton.click();
        Thread.sleep(1000);
        homePage.editCredential("new username","http://www.new.com" , "new password");
        Thread.sleep(500);
        driver.get("http://localhost:" + this.port + "/");
        homePage.openCredentialsTab();
        Thread.sleep(500);
        WebElement credential = driver.findElement(By.cssSelector("td.credential-username-row"));
        Assertions.assertEquals("new username", credential.getText());
    }

    @Test
    @Order(2)
    public void userCanDeleteHisCredential() throws InterruptedException {
        Thread.sleep(500);
        homePage.openCredentialsTab();
        Thread.sleep(500);
        WebElement editButton = driver.findElement(By.cssSelector("a.btn-danger"));
        driver.get("http://localhost:" + this.port + "/");
        homePage.openCredentialsTab();
        WebElement savedCredential = driver.findElement(By.cssSelector("td.credential-username-row"));
        Assertions.assertEquals("", savedCredential.getText());
    }
}