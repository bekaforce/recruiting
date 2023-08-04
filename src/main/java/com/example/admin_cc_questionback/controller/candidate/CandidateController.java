package com.example.admin_cc_questionback.controller.candidate;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.dtos.StageAnalyticsByDateDto;
import com.example.admin_cc_questionback.entities.dtos.CandidateDto;
import com.example.admin_cc_questionback.entities.candidate.Candidate;
import com.example.admin_cc_questionback.entities.dtos.GetStageAnalyticsDto;
import com.example.admin_cc_questionback.entities.dtos.InvitationDto;
import com.example.admin_cc_questionback.service.candidate.impl.CandidateServiceImpl;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Url.ADMIN + Url.API + Url.CANDIDATE)
public class CandidateController {
    private final CandidateServiceImpl candidateService;

    public CandidateController(CandidateServiceImpl candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCandidate(@PathVariable Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate response = candidateService.decodedCandidateById(id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allCandidates/{candidateType_id}")
    public ResponseEntity<?> allCandidatesByType(@PathVariable(value = "candidateType_id") Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<CandidateDto> response = candidateService.all(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allFailedCandidates/{candidateType_id}")
    public ResponseEntity<?> allFailedCandidatesByType(@PathVariable(value = "candidateType_id") Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<CandidateDto> response = candidateService.allFailed(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allCandidatesOnInterviewByType/{candidateType_id}")
    public ResponseEntity<?> allCandidatesOnInterviewByType(@PathVariable(value = "candidateType_id") Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<CandidateDto> response = candidateService.allOnInterview(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allCandidatesOnTest/{candidateType_id}")
    public ResponseEntity<?> allCandidatesOnTest(@PathVariable(value = "candidateType_id") Long id) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        List<CandidateDto> response = candidateService.allOnTest(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping("/allStageAnalytics")
    public ResponseEntity<?> allStageAnalytics(@RequestBody StageAnalyticsByDateDto analyticsPeriodByIdDto) {
        List<GetStageAnalyticsDto> response = candidateService.allStageAnalytics(analyticsPeriodByIdDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_Obuchenie')")
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> comment(@PathVariable(value = "id") Long id, @RequestParam String comment) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String response = candidateService.setComment(id, comment);
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/setStatus/{id}")
    public ResponseEntity<?> setStatus(@PathVariable(value = "id") Long id, @RequestBody InvitationDto invitationDto) throws DecoderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Candidate response = candidateService.setStatus(invitationDto, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/inviteAndReject")
    public ResponseEntity<?> inviteAndReject(){
        List<String> response = candidateService.inviteOrReject();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PutMapping("/setArchive/{id}")
    public ResponseEntity<?> setArchive(@PathVariable(value = "id") Long id, @RequestParam boolean isArchive) {
        Candidate response = candidateService.isArchive(isArchive, id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin')")
    @PostMapping(value = "/download_excel/{candidateTypeId}")
    public void downloadExcel(HttpServletResponse response, @PathVariable(value = "candidateTypeId") Long candidateTypeId) throws DecoderException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Candidates_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        candidateService.excelExporter(response, candidateTypeId);
    }
}
