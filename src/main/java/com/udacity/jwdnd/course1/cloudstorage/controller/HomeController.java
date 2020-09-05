package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/")
    public String showHome(Model model, Authentication authentication) throws Exception {
        Integer userId = userMapper.getUser(authentication.getName()).getUserid();
        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("files", fileService.getFiles(userId));
        model.addAttribute("credentials", credentialService.getAll(userId));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("fileService", fileService);

        return "home";
    }

    @GetMapping("/result")
    public String showResult() {
        return "result";
    }
}
