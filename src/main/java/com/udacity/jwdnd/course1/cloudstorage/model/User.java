package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

    public User(Integer userId, String username, String salt, String password, String firstName, String lastName) {
        this.userid = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
    }
}