package com.example.admin_cc_questionback.service.candidate;

import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface DecoderService {
    String encrypt(String strToEncrypt) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    String decrypt(String strToDecrypt) throws InvalidKeyException, DecoderException, IllegalBlockSizeException, BadPaddingException;
}
