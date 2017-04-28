package com.redhat.ttsaas;

import java.io.IOException;

import com.redhat.ttsaas.model.TextToSynthesize;

public interface SynthesizerService {
    void speak(TextToSynthesize textToSynthesize) throws IOException;
}
