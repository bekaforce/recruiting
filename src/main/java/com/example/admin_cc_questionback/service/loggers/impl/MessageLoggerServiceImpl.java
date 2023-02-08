package com.example.admin_cc_questionback.service.loggers.impl;

import com.example.admin_cc_questionback.entities.loggers.MessageLogger;
import com.example.admin_cc_questionback.repository.loggers.MessageLoggerRepo;
import com.example.admin_cc_questionback.service.loggers.MessageLoggerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageLoggerServiceImpl implements MessageLoggerService {
    private final MessageLoggerRepo messageLoggerRepo;
    private final LoggerService loggerService;

    public MessageLoggerServiceImpl(MessageLoggerRepo messageLoggerRepo, LoggerService loggerService) {
        this.messageLoggerRepo = messageLoggerRepo;
        this.loggerService = loggerService;
    }

    @Override
    public MessageLogger saveUpdate(String nameBefore, String nameNow, String textBefore, String textNow) {
        MessageLogger messageLogger = new MessageLogger();
        messageLogger.setName(loggerService.setParam(nameBefore, nameNow));
        messageLogger.setText(loggerService.setParam(textBefore, textNow));
        messageLogger.setStatus(LoggerStatus.UPDATED);
        messageLogger.setDateTime(loggerService.bishkekNow());
        messageLogger.setLogin(loggerService.login());
        return messageLoggerRepo.save(messageLogger);
    }

    @Override
    public List<MessageLogger> all() {
        return messageLoggerRepo.findAll().stream().sorted(Comparator.comparingLong(MessageLogger::getId).reversed()).collect(Collectors.toList());
    }
}
