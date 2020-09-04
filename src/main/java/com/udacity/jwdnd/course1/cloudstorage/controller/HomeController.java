package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private NoteService noteService;
    private UserMapper userMapper;
    private FileService fileService;
    private CredentialService credentialService;

    public HomeController(UserMapper userMapper, NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping("/")
    public String showHome(Model model, Authentication authentication) throws Exception {
        Integer userId = userMapper.getUser(authentication.getName()).getUserid();
        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("files", fileService.getFiles(userId));
        model.addAttribute("credentials", credentialService.getAll(userId));

        return "home";
    }

    @GetMapping("/result")
    public String showResult() {
        return "result";
    }
}
