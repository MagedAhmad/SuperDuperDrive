package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationTest {
    @LocalServerPort
	private int port;

	private WebDriver driver;

	private LoginPage loginPage;
	private SignupPage signupPage;
    private HomePage homePage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(2)
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
    @Order(3)
	public void loggedOutUsersCannotAccessHomePage() throws InterruptedException{
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(4)
	public void userCanRegisterAndLoginAndLogout() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.registerUser("maged", "ahmed", "maged1", "secret");
        Thread.sleep(500);
		loginPage.loginUser("maged1", "secret");
        Thread.sleep(500);
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.logout();
        Thread.sleep(500);
        driver.get("http://localhost:" + this.port + "/");
        Thread.sleep(500);
		Assertions.assertEquals("Login", driver.getTitle());
	}

}
