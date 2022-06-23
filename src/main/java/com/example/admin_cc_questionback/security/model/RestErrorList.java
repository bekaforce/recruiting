package com.example.admin_cc_questionback.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestErrorList extends ArrayList<ErrorMessage> {


    private HttpStatus status;

    public RestErrorList(HttpStatus status, ErrorMessage... errors) {
        this(status.value(), errors);
    }

    public RestErrorList(int status, ErrorMessage... errors) {
        super();
        this.status = HttpStatus.valueOf(status);
        addAll(asList(errors));
    }

}

