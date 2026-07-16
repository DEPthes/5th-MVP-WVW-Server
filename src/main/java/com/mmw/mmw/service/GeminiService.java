package com.mmw.mmw.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final WebClient.Builder webClientBuilder;

    public String askGemini(String prompt){
        //구글 Gemini API 규격에 맞는 요청 DTO 생성
        GeminiRequest requestBody = new GeminiRequest(prompt);

        try{
            //webclient를 구글 서버로 POST 요청 전송
            GeminiResponse response = webClientBuilder.build()
                    .post()
                    .uri(apiUrl)
                    .header("Content-Type","application/json")
                    .header("X-goog-api-key",apiKey.trim())//AQ로 시작하는 키는 헤더로 보내야함
                    .bodyValue(requestBody)//조립된 JSON 요청 바디를 넣음
                    .retrieve()
                    .bodyToMono(GeminiResponse.class)//응답 결과를 GeminiResponse 객체로 변환
                    .block();//결과를 받을 때 까지 대기(동기 통신)
            //결과 데이터에서 생성된 텍스트 답변만 추출
            if (response != null && !response.getCandidates().isEmpty()){
                return response.getCandidates().get(0).getContent().getParts().get(0).getText();
            }
            return "응답을 받지 못했습니다.";
        }catch (Exception e){
            return "Gemini API 호출 실패 : "+e.getMessage();
        }
    }


    @Data
    public static class GeminiRequest{
        private List<Content> contents;

        public GeminiRequest(String text){
            Part part = new Part();
            part.setText(text);
            Content content = new Content();
            content.setParts(List.of(part));
            this.contents= List.of(content);
        }
    }

    @Data
    public static class GeminiResponse{
        private List<Candidate> candidates;
    }

    @Data
    public static class Candidate{
        private Content content;
    }

    @Data
    public static class Content{
        private List<Part> parts;
    }

    @Data
    public static class Part{
        private String text;
    }
}
