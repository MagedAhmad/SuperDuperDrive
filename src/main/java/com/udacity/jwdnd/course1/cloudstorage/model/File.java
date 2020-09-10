package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class File {
    private int fileid;
    private String filename;
    private String filesize;
    private String contenttype;
    private byte[] filedata;
    private int userid;


}
