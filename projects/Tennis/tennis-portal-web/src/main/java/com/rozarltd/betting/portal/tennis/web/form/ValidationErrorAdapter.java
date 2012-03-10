package com.rozarltd.betting.portal.tennis.web.form;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorAdapter {
    // <field_name, field_error_message>
    private Map<String, String> errors = new HashMap<String, String>();

    public ValidationErrorAdapter(BindingResult bindingResult) {
        if(bindingResult != null) {
            for(FieldError error: bindingResult.getFieldErrors()) {
                this.errors.put(error.getField(), error.getCodes()[0]);
            }
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
