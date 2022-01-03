package com.waracle.cakemanager.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.model.CakeDTO;
import com.waracle.cakemanager.repository.CakeRepository;
import com.waracle.cakemanager.utility.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    private Validator validator;

    private CakeService cakeService;

    @BeforeEach
    void setup() {
        initMocks(this);
        cakeService = new CakeServiceImpl(cakeRepository, validator);
    }

    @Test
    @Tag("Create Cake info -Success")
    public void createCakeInfo_withSuccess() {
        CakeDTO cakeDTORequest = CakeDTO.builder()
                .description("Red Velvet Cake with red sponge")
                .title("Red Velvet Cake")
                .image("red_velvet_image.png").build();
        CakeDTO cakeDTO = singleCakeDTO();
        Cake cake = singleCakeEntity();
        doNothing().when(validator).validateInput(cakeDTORequest);
        when(cakeRepository.save(cake)).thenReturn(cake);
        cakeDTO = cakeService.createCake(cakeDTORequest);
        assertNotNull(cakeDTO);
        assertEquals(cakeDTORequest.getDescription(), cakeDTO.getDescription());
        assertEquals(cakeDTORequest.getTitle(), cakeDTO.getTitle());
        assertEquals(cakeDTORequest.getImage(), cakeDTO.getImage());

    }

    @Test
    @Tag("Get all cake info- Success")
    public void getAllCakes_200Success() {
        List<Cake> allCakeInfo = getCakeInfo();
        when(cakeRepository.findAll()).thenReturn(allCakeInfo);
        List<CakeDTO> cakeDTOS = cakeService.getAllCakes();
        assertNotNull(cakeDTOS);
        assertEquals(cakeDTOS.size(), allCakeInfo.size());
        assertEquals(allCakeInfo.get(0).getDescription(), cakeDTOS.get(0).getDescription());
        assertEquals(allCakeInfo.get(0).getTitle(), cakeDTOS.get(0).getTitle());
        assertEquals(allCakeInfo.get(0).getImage(), cakeDTOS.get(0).getImage());
    }

    private CakeDTO singleCakeDTO() {
        return CakeDTO.builder()
                .description("Red Velvet Cake with red sponge")
                .title("Red Velvet Cake")
                .image("red_velvet_image.png").build();
    }

    private List<Cake> getCakeInfo() {
        return new ArrayList<>(Arrays.asList(
                Cake.builder() .description("Red Velvet Cake with red sponge")
                        .title("Red Velvet Cake")
                        .image("red_velvet_image.png").build(),
                Cake.builder() .description("Vanilla Cake with strawberry flavour")
                        .title("Vanilla Cake")
                        .image("vanilla_image.png").build()));
    }

    private Cake singleCakeEntity() {
        return Cake.builder() .description("Red Velvet Cake with red sponge")
                .title("Red Velvet Cake")
                .image("red_velvet_image.png").build();
    }

}
