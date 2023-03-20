package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.services.candidate.EncoderService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class EncoderServiceImpl implements EncoderService {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec("20002302031022BZ".getBytes(), "AES");

    public EncoderServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @Override
    public String encrypt(String strToEncrypt) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
                cipher.init(Cipher.ENCRYPT_MODE, key);
                return new String(Hex.encodeHex(cipher.doFinal(strToEncrypt.getBytes()), false));
    }

    @Override
    public String decrypt(String strToDecrypt) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Hex.decodeHex(strToDecrypt.toCharArray())));
    }
}
