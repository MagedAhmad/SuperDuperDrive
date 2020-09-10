package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/file")
    public String addFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        if(fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        User user = userMapper.getUser(authentication.getName());

//        check if filename already exists
        File oldFile = fileMapper.findByname(fileUpload.getOriginalFilename(), user.getUserid());
        if(oldFile != null) {
            return "redirect:/result?error";
        }
        fileService.addFile(fileUpload, user.getUserid());

        return "redirect:/result?success";
    }

    @GetMapping("/file/delete")
    public String delete(@RequestParam("id") int fileid, Authentication authentication) {
        if(fileid < 1) {
            return "redirect:/result?error";
        }

        User user = userMapper.getUser(authentication.getName());
        if(user.getUserid() != fileService.findByid(fileid).getUserid()) {
            return "redirect:/result?error";
        }

        fileService.delete(fileid);

        return "redirect:/result?success";
    }

}
