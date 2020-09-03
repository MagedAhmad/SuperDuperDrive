package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialController {
    private CredentialService credentialService;
    private UserMapper userMapper;

    public CredentialController(CredentialService credentialService, UserMapper userMapper) {
        this.credentialService = credentialService;
        this.userMapper = userMapper;
    }

    @PostMapping("/credential")
    public String save(Authentication authentication, Credential credential) {
        User user = userMapper.getUser(authentication.getName());
        if(credential.getCredentialid() != null) {
//            if(user.getUserid() != credential.getUserid()){
//                return "redirect:/result?error";
//            }
            credentialService.update(credential);
        }else {
            credentialService.add(credential, user.getUserid());
        }

        return "redirect:/result?success";
    }

    @GetMapping("/credential/delete")
    public String delete(@RequestParam("id") int noteid) {
        if(noteid < 1) {
            return "redirect:/result?error";
        }
        credentialService.delete(noteid);

        return "redirect:/result?success";
    }
}
