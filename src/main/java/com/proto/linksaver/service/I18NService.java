package com.proto.linksaver.service;


import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class I18NService {
    private final ResourceBundle messageBundle;

    public I18NService(ResourceBundle messageBundle) {
        this.messageBundle = messageBundle;
    }

    public String getMessage(Enum key) {
        return messageBundle.getString(key.name());
    }
}
