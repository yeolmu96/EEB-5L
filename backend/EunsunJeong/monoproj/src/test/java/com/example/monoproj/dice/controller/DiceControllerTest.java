package com.example.monoproj.dice.controller;

import com.example.monoproj.dice.controller.request_form.DiceRollResultRequestForm;
import com.example.monoproj.dice.service.DiceService;
import com.example.monoproj.dice.service.DiceServiceImpl;
import com.example.monoproj.redis_cache.service.RedisCacheService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiceControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private DiceService diceService;

    @Mock
    private RedisCacheService redisCacheService;

    @InjectMocks
    private DiceController diceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(diceController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveRollResult() throws Exception {
        DiceRollResultRequestForm requestForm = new DiceRollResultRequestForm(1L, 3);
        String token = "Bearer mock-token";
        Long mockAccountId = 123L;

        when(redisCacheService.getValueByKey("mock-token")).thenReturn(mockAccountId);
        when(diceService.saveRollResult(any(), any(), any())).thenReturn(true);

        mockMvc.perform(post("/dice/save-roll-result")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(requestForm)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(diceService).saveRollResult(any(), any(), any());
    }
}