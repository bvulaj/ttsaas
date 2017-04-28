package com.redhat.ttsaas.synthesize;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Service;

import com.redhat.ttsaas.SynthesizerService;
import com.redhat.ttsaas.model.TextToSynthesize;

@Service
public class EspeakSynthesizerService implements SynthesizerService {

    @Override
    public void speak(TextToSynthesize tts) throws IOException {
        StringBuffer cmd = new StringBuffer("espeak ")
                .append('"').append(tts.getText()).append('"')
                .append(" -v ").append(tts.getLanguage()).append("+").append(randomGender());
        CommandLine cmdLine = CommandLine.parse(cmd.toString());

        new DefaultExecutor().execute(cmdLine);
    }

    private String randomGender() {
        return new Random().nextInt(10) % 2 == 0 ? "m1" : "f1";
    }

}
