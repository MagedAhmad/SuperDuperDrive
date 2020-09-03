package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    private FileService fileService;
    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileController(FileService fileService, FileMapper fileMapper, UserMapper userMapper) {
        this.fileService = fileService;
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/file")
    public String addFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        if(fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        User user = userMapper.getUser(authentication.getName());
        fileService.addFile(fileUpload, user.getUserid());

        return "redirect:/result?success";
    }

    @GetMapping("/file/delete")
    public String delete(@RequestParam("id") int fileid) {
        if(fileid < 1) {
            return "redirect:/result?error";
        }
        fileService.delete(fileid);

        return "redirect:/result?success";
    }
}
