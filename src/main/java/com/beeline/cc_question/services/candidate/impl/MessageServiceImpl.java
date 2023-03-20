package com.beeline.cc_question.services.candidate.impl;

import com.beeline.cc_question.entities.candidate.Message;
import com.beeline.cc_question.entities.dtos.interview.SuccessDto;
import com.beeline.cc_question.repos.candidate.MessageRepo;
import com.beeline.cc_question.services.candidate.MessageService;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Message messageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    @Override
    public String getText(Long id) {
        Message message = messageById(id);
        if (message != null){
            return message.getText();
        }
        else {
            return null;
        }
    }

    @Override
    public SuccessDto success(String name, String candidate_type) {
        Long title = 3L;
        Long text = 4L;
        Long description = 5L;
        SuccessDto successDto = new SuccessDto();
        successDto.setTitle(setSuccessText(name, candidate_type, title));
        successDto.setText(setSuccessText(name, candidate_type, text));
        successDto.setDescription(setSuccessText(name, candidate_type, description));
        return successDto;
    }

    @Override
    public String setSuccessText(String name, String candidate_type, Long message_id) {
        String text = getText(message_id);
        text = text.replaceAll("name", name);
        text = text.replaceAll("candidate_type", candidate_type);
        return text;
    }

    @Override
    public String setAuthEmailText(String name, String password, Long message_id) {
        String text = getText(message_id);
        text = text.replaceAll("name", name);
        text = text.replaceAll("password", password);
        return text;
    }


}
