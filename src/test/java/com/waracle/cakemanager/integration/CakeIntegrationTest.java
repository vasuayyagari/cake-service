package com.waracle.cakemanager.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.waracle.cakemanager.CakeManagerApplication;
import com.waracle.cakemanager.model.CakeDTO;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CakeManagerApplication.class)
@AutoConfigureMockMvc
public class CakeIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("All Cake Info - with data")
    public void getAllCakes() throws Exception {
        CakeDTO cakeDTO = singleCakeDTO();
        createCakeInfo();
        List<CakeDTO> cakeDTOS = getCakeInfo();
        assertNotNull(cakeDTOS);
        assertEquals(cakeDTO.getDescription(), cakeDTOS.get(0).getDescription());
        assertEquals(cakeDTO.getImage(), cakeDTOS.get(0).getImage());
        assertEquals(cakeDTO.getTitle(), cakeDTOS.get(0).getTitle());
    }

    @Test
    @Tag("Create Cake Info - Success 201")
    public void createAllCakes() throws Exception {
        mockMvc.perform(post("/cake").content(objectMapper.writeValueAsString(singleCakeDTO()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @Tag("Create Cake Info - Fail 400")
    public void createCake_WithFail() throws Exception {
        mockMvc.perform(post("/cake").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("Create Cake Info - Fail 415")
    public void createCake_WithFail_WithWrongOutputFormat() throws Exception {
        mockMvc.perform(post("/cake").contentType(MediaType.APPLICATION_ATOM_XML)).andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    private void createCakeInfo() throws Exception {
        mockMvc.perform(post("/cake").content(objectMapper.writeValueAsString(singleCakeDTO()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());

    }

    private List<CakeDTO> getCakeInfo() throws Exception {

        MvcResult result = mockMvc.perform(get("/cakes").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();

        ArrayList<Object> returnedResult = objectMapper.readValue(result.getResponse().getContentAsString(),
                ArrayList.class);
        return convertResponseIntoJsonList(returnedResult);
    }

    private CakeDTO singleCakeDTO() {
        return CakeDTO.builder()
                .description("red velvet cake made sponge")
                .title("Red Velvet Cake")
                .image("red_velvet_cake.png")
                .build();
    }

    private List<CakeDTO> convertResponseIntoJsonList(ArrayList<Object> response)
            throws JsonProcessingException {
        List<CakeDTO> items = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Object test : response) {
            String jsonString = new Gson().toJson(test, Map.class);
            items.add(mapper.readValue(jsonString, new TypeReference<CakeDTO>() {
            }));
        }
        return items;
    }

}
