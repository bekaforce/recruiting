package com.beeline.cc_question.services.candidate;

import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface EncoderService {
    String encrypt(String strToEncrypt) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    String decrypt(String strToDecrypt) throws InvalidKeyException, DecoderException, IllegalBlockSizeException, BadPaddingException;
}
