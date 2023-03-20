package com.beeline.cc_question.services.candidate;

import com.beeline.cc_question.entities.candidate.Experience;
import com.beeline.cc_question.entities.dtos.candidate.CandidateDto;
import com.beeline.cc_question.entities.candidate.Candidate;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CandidateService {
    Candidate save(CandidateDto candidateDto) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate candidateById(Long id);
    Candidate decodedCandidateById(Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate setStage(Candidate candidate, String stage);
    String checkCandidate(String name, String surname, LocalDate birthday);
    String getStage(Candidate candidate);
    Candidate candidateByEmail(String email) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    boolean expiration(LocalDateTime registration_date);
    Experience setExperience(Experience experience);
    Candidate encodePersonalInfo(Candidate candidate) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate decodePersonalInfo(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    String formatDateToString(LocalDate date);
}
