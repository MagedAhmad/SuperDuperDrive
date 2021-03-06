package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File findByid(int fileid);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userid}")
    File findByname(String filename, int userid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> findByUserId(int userid);

    @Insert("INSERT INTO FILES (filename, filesize, contenttype, filedata, userid) VALUES (#{file.filename}, #{file.size}, #{file.contentType}, #{file.bytes}, #{userid})")
    int insert(MultipartFile file, int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    int delete(int fileid);
}
