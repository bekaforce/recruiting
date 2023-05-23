package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.candidate.MailMessage;
import com.example.admin_cc_questionback.entities.dtos.InvitationDto;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface EmailSenderService {
    boolean sendEmail(Candidate candidate, String introduction, String body) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    boolean sendMessage(MailMessage mailMessage);
    boolean sendCongratulationMessage(Candidate candidate, InvitationDto invitationDto) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
