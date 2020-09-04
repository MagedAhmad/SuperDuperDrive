package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "logout")
    private WebElement logout;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "credential-username")
    private WebElement credentialsUsername;

    @FindBy(id = "credential-url")
    private WebElement credentialsUrl;

    @FindBy(id = "credential-password")
    private WebElement credentialsPassword;

    @FindBy(id = "saveNoteButton")
    private WebElement noteSubmit;

    @FindBy(id = "saveCredentialButton")
    private WebElement credentialSubmit;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "show-note-modal")
    private WebElement noteModal;

    @FindBy(id = "show-credentials-modal")
    private WebElement credentialsModal;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logout.click();
    }

    public void saveNote(String title, String description) throws InterruptedException {
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        noteSubmit.click();
    }

    public void saveCredential(String username, String url, String password) throws InterruptedException {
        credentialsUsername.sendKeys(username);
        credentialsUrl.sendKeys(url);
        credentialsPassword.sendKeys(password);
        credentialSubmit.click();
    }

    public void openNotesTab() {
        notesTab.click();
    }

    public void openCredentialsTab() {
        credentialsTab.click();
    }

    public void openNoteModal() {
        noteModal.click();
    }
    public void openCredentialsModal() {
        credentialsModal.click();
    }

    public void editNote(String title, String description) throws InterruptedException{
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
		noteSubmit.click();
    }

    public void editCredential(String username, String url, String password) throws InterruptedException{
        credentialsUsername.clear();
        credentialsUsername.sendKeys(username);
        credentialsUrl.clear();
        credentialsUrl.sendKeys(url);
        credentialsPassword.clear();
        credentialsPassword.sendKeys(password);
		credentialSubmit.click();
    }
}
