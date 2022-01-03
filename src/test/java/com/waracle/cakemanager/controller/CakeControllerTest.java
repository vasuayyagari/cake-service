package com.waracle.cakemanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.waracle.cakemanager.model.CakeDTO;
import com.waracle.cakemanager.service.CakeService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@ContextConfiguration(classes = CakeController.class)
public class CakeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CakeService cakeService;

    @Test
    @Tag("Create Cake: test 201 success")
    public void createCake_with201Success() throws Exception {
        mockMvc.perform(post("/cake").content(objectMapper.writeValueAsString(createRequestPayload()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @Tag("Create cake: test 400 bad request")
    public void createCake_with400BadRequest() throws Exception {
        mockMvc.perform(post("/cake").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("Create cake: test 415 unsupported media type")
    public void createCake_with415UnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/cake").contentType(MediaType.APPLICATION_ATOM_XML)).andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @Tag("Create interest: test 405 method not allowed")
    public void createCake_with405UnsupportedMediaType() throws Exception {
        mockMvc.perform(get("/cake").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @Tag("Get all Cake Info: 200 success")
    public void getAllInterestInfo_with200Success() throws Exception {
        List<CakeDTO> cakeInfo = getCakeInfo();
        when(cakeService.getAllCakes()).thenReturn(cakeInfo);
        MvcResult result = mockMvc.perform(get("/cakes").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();

        String expectedJsonResponseString = objectMapper.writeValueAsString(cakeInfo);
        assertEquals(expectedJsonResponseString, result.getResponse().getContentAsString());
    }

    @Test
    @Tag("Get all Cake Info: 405 Method not allowed")
    public void getAllCakeInfo_with405UnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/cakes").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    private CakeDTO createRequestPayload() {
        CakeDTO cakeDTO = CakeDTO.builder()
                .description("Red Velvet Cake with red sponge")
                .title("Red Velvet Cake")
                .image("red_velvet_image.png")
                .build();
        return cakeDTO;
    }

    private List<CakeDTO> getCakeInfo() {
        return new ArrayList<CakeDTO>(Arrays.asList(
                CakeDTO.builder()
                        .description("Red Velvet Cake with red sponge")
                        .title("Red Velvet Cake")
                        .image("red_velvet_image.png").build(),
                CakeDTO.builder()
                        .description("Vanilla cake with strawberry flavour")
                        .title("Vanilla Cake")
                        .image("vanilla_cake.png").build()));
    }

}
