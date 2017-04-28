package com.redhat.ttsaas;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
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

	@GetMapping
	public String home(Model model) {
		model.addAttribute("tts", new TextToSynthesize());
		return "index";
	}

	@PostMapping("/tts")
	public @ResponseBody void readText(@RequestBody(required = false) TextToSynthesize tts, HttpServletResponse response) throws IOException {
	    CommandLine cmdLine = CommandLine.parse("espeak \"" + tts.getText() + "\"");
        new DefaultExecutor().execute(cmdLine);
	}

	// can't figure out how to quickly support form and REST in the same method right now.  fix later.
	@PostMapping("/ttsform")
	public @ResponseBody void readTextForm(@ModelAttribute TextToSynthesize tts, HttpServletResponse response) throws IOException {
		readText(tts, response);
	}
}
