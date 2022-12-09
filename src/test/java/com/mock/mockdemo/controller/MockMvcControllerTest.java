package com.mock.mockdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mockdemo.dto.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MockMvcController.class)
class MockMvcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글 깨짐 처리
                .build();
    }

    @Test
    public void test_post() throws Exception{
        String param = objectMapper.writeValueAsString(new Info("shbae", "shbaeTest"));

        mockMvc.perform(post("/mockmvc")
                .content(param)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("shbae님의 테스트입니다. shbaeTest"))
                .andDo(print());
    }

    @Test
    public void test_get() throws Exception{
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("name", "shbae");
        info.add("id", "shbae94");

        mockMvc.perform(get("/mockmvc")
                .params(info))
                .andExpect(status().isOk())
                .andExpect(content().string("shbae의 MockMvc 테스트입니다. shbae94"))
                .andDo(print());
    }



}