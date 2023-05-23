package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.loggers.InvitationLogger;
import org.aopalliance.intercept.Invocation;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.List;

public interface InvitationLoggerService {
    InvitationLogger save(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    List<InvitationLogger> getAll() throws InvalidKeyException, DecoderException, BadPaddingException;

}
