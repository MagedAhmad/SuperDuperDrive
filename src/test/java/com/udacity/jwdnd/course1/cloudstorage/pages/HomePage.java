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

    @FindBy(id = "saveNoteButton")
    private WebElement noteSubmit;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "show-note-modal")
    private WebElement noteModal;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logout.click();
    }

    public void saveNote(String title, String description) throws InterruptedException {
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        Thread.sleep(500);
        noteSubmit.click();
    }

    public void openNotesTab() {
        notesTab.click();
    }

    public void openNoteModal() {
        noteModal.click();
    }

    public void editNote(String title, String description) throws InterruptedException{
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        Thread.sleep(500);
		noteSubmit.click();
    }
}
