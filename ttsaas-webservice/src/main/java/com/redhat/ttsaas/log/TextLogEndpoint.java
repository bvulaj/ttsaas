package com.redhat.ttsaas.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import com.redhat.ttsaas.model.TextToSynthesize;

@Component
public class TextLogEndpoint implements Endpoint<List<TextToSynthesize>> {

    @Autowired
    private List<TextToSynthesize> textCache;

    @Override
    public String getId() {
        return "textlog";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public List<TextToSynthesize> invoke() {
        return textCache;
    }
}
