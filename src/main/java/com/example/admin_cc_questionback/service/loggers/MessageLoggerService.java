package com.example.admin_cc_questionback.service.loggers;

import com.example.admin_cc_questionback.entities.loggers.MessageLogger;

import java.util.List;

public interface MessageLoggerService {
    MessageLogger saveUpdate(String nameBefore, String nameNow, String textBefore, String textNow);
    List<MessageLogger> all();
}
