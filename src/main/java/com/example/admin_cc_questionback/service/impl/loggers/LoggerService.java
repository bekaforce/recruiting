package com.example.admin_cc_questionback.service.impl.loggers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class LoggerService {
    public String empty = "-";
    public final static String bishkek = "Asia/Bishkek";

    public boolean beforeAndAfter(String before, String after) {
        return before.equals(after);
    }

    public String login(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public LocalDateTime bishkekNow(){
        return LocalDateTime.now(ZoneId.of(LoggerService.bishkek));
    }

    public String setParam(String before, String after) {
        if (!beforeAndAfter(before, after)){
            return before + " на " + after;
        }
        return "Не изменилось";
    }
}
