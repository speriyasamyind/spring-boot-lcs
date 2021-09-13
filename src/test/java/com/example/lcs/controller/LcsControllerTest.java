package com.example.lcs.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.lcs.dto.LcsInput;
import com.example.lcs.dto.LcsOutput;
import com.example.lcs.service.LcsService;
import com.google.gson.Gson;


@WebMvcTest(controllers = LcsController.class)
public class LcsControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean
	  private LcsService lcsService;
	  
	  @BeforeEach
	  public void init() {
	    MockitoAnnotations.openMocks(this);
	  }

	  @Test
	  @DisplayName("Should Give 200 for Valid input")
	  public void test_valid_input() throws Exception {
	    Gson gson = new Gson();
	    LcsInput lcsInput = new LcsInput();
	    String body = gson.toJson(lcsInput);
	    when(lcsService.getLcsStrings(isA(LcsInput.class))).thenReturn(new LcsOutput());

	    mockMvc.perform(post("/lcs").content(body).contentType(MediaType.APPLICATION_JSON_VALUE)
	        .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is(HttpStatus.OK.value())).andReturn();
	  }
	  
	  @Test
	  @DisplayName("Should Give 400 for invalid / empty input")
	  public void test_invalid_input() throws Exception {
	    String body = "";
	    mockMvc.perform(post("/lcs").content(body).contentType(MediaType.APPLICATION_JSON_VALUE)
	        .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andReturn();
	  }
}
