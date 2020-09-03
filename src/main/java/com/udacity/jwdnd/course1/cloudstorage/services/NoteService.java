package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;

    public void addNote(Note note) {
        noteMapper.insert(note);
    }

    public void editNote(Note note) {
        noteMapper.edit(note);
    }

    public void deleteNote(int noteid) {
        noteMapper.delete(noteid);
    }

    public List<Note> getNotes(Integer userid) {
        return noteMapper.findByUserId(userid);
    }
}
