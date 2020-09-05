package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    public List<Credential> getAll(int userid) throws Exception {
        List<Credential> credentials = credentialMapper.findByUserId(userid);

        return credentials;
    }

    public void add(Credential credential, int userid) {
        credentialMapper.insert(encryptPassword(credential), userid);
    }

    private Credential encryptPassword(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));

        return credential;
    }

    public void update(Credential credential) {
        credentialMapper.update(encryptPassword(credential));
    }

    public void delete(int credentialid) {
        credentialMapper.delete(credentialid);
    }
}
