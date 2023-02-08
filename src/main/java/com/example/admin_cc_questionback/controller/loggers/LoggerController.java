package com.example.admin_cc_questionback.controller.loggers;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.entities.loggers.*;
import com.example.admin_cc_questionback.service.loggers.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = Url.ADMIN + Url.API + LoggerUrl.LOG)
public class LoggerController {
    private final AnswerLoggerServiceImpl answerLoggerService;
    private final CandidateTypeLoggerServiceImpl candidateTypeLoggerService;
    private final DepartmentLoggerServiceImpl departmentLoggerService;
    private final KnowledgeTypeLoggerServiceImpl knowledgeTypeLoggerService;
    private final KnowledgeLoggerServiceImpl knowledgeLoggerService;
    private final LevelLoggerServiceImpl levelLoggerService;
    private final MessageLoggerServiceImpl messageLoggerService;
    private final QuestionLoggerServiceImpl questionLoggerService;
    private final SignInLoggerServiceImpl signInLoggerService;
    private final TeamTypeLoggerServiceImpl teamTypeLoggerService;

    public LoggerController(AnswerLoggerServiceImpl answerLoggerService, CandidateTypeLoggerServiceImpl candidateTypeLoggerService, DepartmentLoggerServiceImpl departmentLoggerService, KnowledgeTypeLoggerServiceImpl knowledgeTypeLoggerService, KnowledgeLoggerServiceImpl knowledgeLoggerService, LevelLoggerServiceImpl levelLoggerService, MessageLoggerServiceImpl messageLoggerService, QuestionLoggerServiceImpl questionLoggerService, SignInLoggerServiceImpl signInLoggerService, TeamTypeLoggerServiceImpl teamTypeLoggerService) {
        this.answerLoggerService = answerLoggerService;
        this.candidateTypeLoggerService = candidateTypeLoggerService;
        this.departmentLoggerService = departmentLoggerService;
        this.knowledgeTypeLoggerService = knowledgeTypeLoggerService;
        this.knowledgeLoggerService = knowledgeLoggerService;
        this.levelLoggerService = levelLoggerService;
        this.messageLoggerService = messageLoggerService;
        this.questionLoggerService = questionLoggerService;
        this.signInLoggerService = signInLoggerService;
        this.teamTypeLoggerService = teamTypeLoggerService;
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.ANSWER)
    public ResponseEntity<?> answerLogs(){
        List<AnswerLogger> response = answerLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.CANDIDATETYPE)
    public ResponseEntity<?> candidateTypeLogs(){
        List<CandidateTypeLogger> response = candidateTypeLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.DEPARTMENT)
    public ResponseEntity<?> departmentLogs(){
        List<DepartmentLogger> response = departmentLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.KNOWLEDGETYPE)
    public ResponseEntity<?> knowledgeTypeLogs(){
        List<KnowledgeTypeLogger> response = knowledgeTypeLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.KNOWLEDGE)
    public ResponseEntity<?> knowledgeLogs(){
        List<KnowledgeLogger> response = knowledgeLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.LEVEL)
    public ResponseEntity<?> levelLogs(){
        List<LevelLogger> response = levelLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.MESSAGE)
    public ResponseEntity<?> messageLogs(){
        List<MessageLogger> response = messageLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.QUESTION)
    public ResponseEntity<?> questionLogs(){
        List<QuestionLogger> response = questionLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.SIGN_IN)
    public ResponseEntity<?> sing_inLogs(){
        List<SignInLogger> response = signInLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('APP_Recruiting_Admin', 'APP_Recruiting_it_audit')")
    @GetMapping(Url.TEAM_TYPE)
    public ResponseEntity<?> team_type_Logs(){
        List<TeamTypeLogger> response = teamTypeLoggerService.all();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
