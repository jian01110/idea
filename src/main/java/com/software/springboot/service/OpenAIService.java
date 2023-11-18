package com.software.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("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")
    private String apiKey;

    private  String apiUrl = "https://api.openai.com/v1/chat/completions";
    private  RestTemplate restTemplate;
    @Autowired
    public OpenAIService(SimpleClientHttpRequestFactory httpRequestFactory) {
        this.restTemplate = new RestTemplate(httpRequestFactory);
    }

    public String callOpenAI(String prompt) {
        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        // 构建请求体
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content",prompt);
        }});
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("max_tokens", 200);
        requestBody.put("messages", dataList);
        requestBody.put("model","gpt-3.5-turbo");
        // 构建请求对象
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // 发送 POST 请求
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // 处理响应
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // 处理错误情况
            return "Error calling OpenAI API";
        }
    }
}