package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    private NoteService noteService;
    private UserMapper userMapper;

    public NoteController(NoteService noteService, UserMapper userMapper) {
        this.noteService = noteService;
        this.userMapper = userMapper;
    }

    @PostMapping("/note")
    public String saveNote(Authentication authentication, Note note) {
        User user = userMapper.getUser(authentication.getName());
        if(note.getNoteid() != null) {
//            if(user.getUserid() != note.getUserid()){
//                return "redirect:/result?error";
//            }
            noteService.editNote(note);
        }else {
            note.setUserid(user.getUserid());
            noteService.addNote(note);
        }

        return "redirect:/result?success";
    }

    @GetMapping("/note/delete")
    public String delete(@RequestParam("id") int noteid) {
        if(noteid < 1) {
            return "redirect:/result?error";
        }
        noteService.deleteNote(noteid);

        return "redirect:/result?success";
    }
}
