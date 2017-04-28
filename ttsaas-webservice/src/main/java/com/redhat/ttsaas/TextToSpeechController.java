package com.redhat.ttsaas;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	public @ResponseBody void readText(@ModelAttribute TextToSynthesize tts, HttpServletResponse response) throws IOException {
		// do the tts
		byte[] speechBytes = tts.getText().getBytes();

		response.getOutputStream().write(speechBytes);
	}
}
