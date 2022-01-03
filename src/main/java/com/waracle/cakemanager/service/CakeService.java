package com.waracle.cakemanager.service;

import com.waracle.cakemanager.model.CakeDTO;

import java.util.List;

public interface CakeService {

    /**
     * A method to create a collection with the given cakeDTO
     * @Param CakeDTO
     * @return CakeDTO
     */
    CakeDTO createCake(CakeDTO cakeDTO);

    /**
     * A method to retrieve all elements from cakes collection
     * @return List<CakeDTO>
     */
    List<CakeDTO> getAllCakes();
}
