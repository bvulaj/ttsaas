package com.redhat.ttsaas.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redhat.ttsaas.SynthesizerService;
import com.redhat.ttsaas.model.TextToSynthesize;

@Controller("/")
public class TextToSpeechController {

    @Autowired
    private List<TextToSynthesize> textCache;

    @Autowired
    private SynthesizerService synthesizer;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("tts", new TextToSynthesize());
        return "index";
    }

    @PostMapping("/tts")
    public @ResponseBody void readText(@RequestBody(required = false) TextToSynthesize tts) throws IOException {
        tts.setDateTime(new Date());
        tts.setLanguage(StringUtils.defaultString(StringUtils.trimToNull(tts.getLanguage()), "en"));

        synthesizer.speak(tts);

        textCache.add(tts);
    }

    @PostMapping("/ttsform")
    public String readTextForm(@ModelAttribute TextToSynthesize tts) throws IOException {
        readText(tts);
        return "redirect:/";
    }

}
