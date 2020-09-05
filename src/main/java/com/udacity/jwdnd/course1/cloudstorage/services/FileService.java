package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public void addFile(MultipartFile file, int userid) {
        fileMapper.insert(file, userid);
    }

    public List<File> getFiles(Integer userid) {
        return fileMapper.findByUserId(userid);
    }

    public void delete(int fileid) {
        fileMapper.delete(fileid);
    }

    public String getFileData(int fileid) {
        File file = fileMapper.findByid(fileid);
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
        return dataURL;
    }
}
