package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface GuestService {
    Candidate add(CandidateDto candidateDto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    int generateDigits();
    Long setPassword(String password, Long id);
    SuccessDto success(Long candidate_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
