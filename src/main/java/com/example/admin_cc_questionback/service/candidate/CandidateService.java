package com.example.admin_cc_questionback.service.candidate;

import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.dtos.GetCandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.dtos.InvitationDto;
import com.example.admin_cc_questionback.entities.interview.Essay;
import com.example.admin_cc_questionback.entities.interview.VideoResult;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.List;

public interface CandidateService {
    Candidate encodedCandidateById(Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate decodedCandidateById(Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    List<CandidateDto> all(Long candidateType_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    List<CandidateDto> allFailed(Long candidateType_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    List<CandidateDto> allOnInterview(Long candidateType_id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    String setComment(Long id, String comment) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate setStatus(InvitationDto invitationDto, Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    List<VideoResult> sortVideoResults(List<VideoResult> videoResults);
    List<Essay> sortEssays(List<Essay> essays);
    List<String> inviteOrReject();
    void checkCandidateAndSaveParticipant(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate encodePersonalInfo(Candidate candidate) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate decodePersonalInfo(Candidate candidate) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    List<CandidateDto> decodeCandidates(List<GetCandidateDto> list) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    Candidate isArchive(boolean isArchive, Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
