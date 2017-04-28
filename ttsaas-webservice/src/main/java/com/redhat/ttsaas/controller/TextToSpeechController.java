package com.redhat.ttsaas.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redhat.ttsaas.model.TextToSynthesize;

@Controller("/")
public class TextToSpeechController {

    @Autowired
    private List<TextToSynthesize> textCache;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("tts", new TextToSynthesize());
        return "index";
    }

    @PostMapping("/tts")
    public @ResponseBody void readText(@RequestBody(required = false) TextToSynthesize tts) throws IOException {
        StringBuffer cmd = new StringBuffer("espeak ")
                .append('"').append(tts.getText()).append('"')
                .append(" -v en+").append(randomGender());
        CommandLine cmdLine = CommandLine.parse(cmd.toString());

        tts.setDateTime(new Date());
        textCache.add(tts);

        new DefaultExecutor().execute(cmdLine);
    }

    @PostMapping("/ttsform")
    public String readTextForm(@ModelAttribute TextToSynthesize tts) throws IOException {
        readText(tts);
        return "redirect:/";
    }

    private String randomGender() {
        return new Random().nextInt(10) % 2 == 0 ? "m1" : "f1";
    }
}
