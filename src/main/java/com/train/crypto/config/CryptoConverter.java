package com.train.crypto.config;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.AttributeConverter;

import java.security.Key;
import java.util.Base64;

public class CryptoConverter implements AttributeConverter<String, String>{

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    @Value("${api.security.secret}")
    private String secretKey;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        try{
            Key key = new SecretKeySpec(secretKey.trim().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Erro na criptografia: ", e);
        }
        
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try{
            Key key = new SecretKeySpec(secretKey.trim().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception e) {
            throw new RuntimeException("Erro na criptografia: ", e);
        }
    }
    
}
