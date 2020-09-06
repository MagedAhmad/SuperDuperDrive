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
public class NotesTest {
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

	public void signup() {
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.registerUser("maged", "ahmed", "maged", "secret");
	}

	public void login() {
		loginPage.loginUser("maged", "secret");
	}

	@Test
	@Order(1)
	public void userCanStoreAndUpdateNote() throws InterruptedException{
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
		WebElement editButton = driver.findElement(By.cssSelector("button.btn-success"));
		editButton.click();
		Thread.sleep(500);
		homePage.editNote("new title", "new Description");
		Thread.sleep(500);
		driver.get("http://localhost:" + this.port + "/");
		homePage.openNotesTab();
		Thread.sleep(500);
		WebElement note = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("new title", note.getText());
	}

	@Test
	@Order(2)
	public void userCanDeleteHisNote() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		this.login();
		Thread.sleep(500);
		homePage.openNotesTab();
		WebElement editButton = driver.findElement(By.cssSelector("a.btn-danger"));
		driver.get("http://localhost:" + this.port + "/");
		homePage.openNotesTab();
		WebElement savedNote = driver.findElement(By.cssSelector("th.note-title-row"));
		Assertions.assertEquals("", savedNote.getText());
	}

}
