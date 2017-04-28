package com.redhat.ttsaas;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TextToSpeechController {

	@PostMapping("/tts")
	public @ResponseBody void readText(@RequestBody String text, HttpServletResponse response) throws IOException {
		// do the tts
		byte[] speechBytes = text.getBytes();

		response.getOutputStream().write(speechBytes);
	}
}
