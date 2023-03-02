package com.juno.spring.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class AsyncControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @RepeatedTest(value = 1000, name = "비동기 테스트 {currentRepetition}/{totalRepetitions}")
    void asyncTest(RepetitionInfo repetitionInfo) throws Exception {
        String url = "/async/"+repetitionInfo.getCurrentRepetition();
        mockMvc.perform(get(url));
    }

    @RepeatedTest(value = 1000, name = "동기 테스트 {currentRepetition}/{totalRepetitions}")
    void noAsyncTest(RepetitionInfo repetitionInfo) throws Exception {
        String url = "/no-async/"+repetitionInfo.getCurrentRepetition();
        mockMvc.perform(get(url));
    }
}