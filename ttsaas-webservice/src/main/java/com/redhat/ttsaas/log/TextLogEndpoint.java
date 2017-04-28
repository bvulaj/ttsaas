package com.redhat.ttsaas.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.redhat.ttsaas.model.TextToSynthesize;

@Component
@ConfigurationProperties(prefix = "endpoints.textlog")
public class TextLogEndpoint extends AbstractEndpoint<List<TextToSynthesize>> {

    @Autowired
    private List<TextToSynthesize> textCache;

    public TextLogEndpoint() {
        super("textlog", false, true);
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
