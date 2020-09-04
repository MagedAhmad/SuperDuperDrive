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
class CloudStorageApplicationTests {

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
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void unauthorizedUsersCannotAccessHomePage() {
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void userCanRegisterAndLogin() throws InterruptedException{
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.registerUser("maged", "ahmed", "maged", "secret");
		Thread.sleep(1000);
		loginPage.loginUser("maged", "secret");
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void loggedOutUsersCannotAccessHomePage() throws InterruptedException{
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.registerUser("maged", "ahmed", "maged", "secret");
		Thread.sleep(1000);
		loginPage.loginUser("maged", "secret");
		Thread.sleep(1000);
		homePage.logout();
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	public void signup() {
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.registerUser("maged", "ahmed", "maged", "secret");
	}

	public void login() {
		loginPage.loginUser("maged", "secret");
	}

	@Test
	public void userCanStoreNote() throws InterruptedException{
		this.signup();
		Thread.sleep(500);
		this.login();
		Thread.sleep(500);
		homePage.openNotesTab();
		Thread.sleep(500);
		homePage.openNoteModal();
		Thread.sleep(500);
		homePage.saveNote("note", "something to say");
		Thread.sleep(500);
		driver.get("http://localhost:" + this.port + "/");
		homePage.openNotesTab();
		Thread.sleep(500);
		WebElement savedNote = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("note", savedNote.getText());
	}

	@Test
	public void userCanEditHisNotes() throws InterruptedException {
		this.userCanStoreNote();
		Thread.sleep(500);
		WebElement editButton = driver.findElement(By.cssSelector("button.btn-success"));
		editButton.click();
		Thread.sleep(500);
		homePage.editNote("new title", "new Description");
		Thread.sleep(500);
		driver.get("http://localhost:" + this.port + "/");
		homePage.openNotesTab();
		Thread.sleep(500);
		WebElement savedNote = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("new title", savedNote.getText());
	}

	@Test
	public void userCanDeleteHisNote() throws InterruptedException {
		this.userCanStoreNote();
		Thread.sleep(500);
		WebElement editButton = driver.findElement(By.cssSelector("a.btn-danger"));
		driver.get("http://localhost:" + this.port + "/");
		homePage.openNotesTab();
		WebElement savedNote = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("", savedNote.getText());
	}

	@Test
	public void userCanStoreCredentials() throws InterruptedException{
		this.signup();
		Thread.sleep(500);
		this.login();
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
	}

	@Test
	public void userCanEditCredentials() throws InterruptedException {
		this.userCanStoreCredentials();
		Thread.sleep(500);
		WebElement editButton = driver.findElement(By.cssSelector("button.btn-success"));
		editButton.click();
		Thread.sleep(500);
		homePage.editCredential("new username","http://www.new.com" , "new password");
		Thread.sleep(500);
		driver.get("http://localhost:" + this.port + "/");
		homePage.openCredentialsTab();
		Thread.sleep(500);
		WebElement savedCredential = driver.findElement(By.cssSelector("td.credential-username-row"));
		Assertions.assertEquals("new username", savedCredential.getText());
	}

}
