package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> findByUserId(int userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password}, #{userid})")
    int insert(Credential credential, int userid);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username} , key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    int update(Credential credential);


    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int delete(int credentialid);
}
