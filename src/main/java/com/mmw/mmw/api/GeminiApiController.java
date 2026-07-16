package com.mmw.mmw.api;

import com.mmw.mmw.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GeminiApiController {
    private final GeminiService geminiService;

    //질의응답 Gemini 테스트
    @GetMapping("api/testgemini")
    public String testGemini(@RequestParam("prompt") String prompt){
        return geminiService.askGemini(prompt);
    }
}
