package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.model.CakeDTO;
import com.waracle.cakemanager.utility.Validator;
import com.waracle.cakemanager.repository.CakeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CakeServiceImpl implements CakeService {

	@Autowired
	private final CakeRepository cakeRepository;
	@Autowired
	private final Validator validator;

	final Logger logger = LoggerFactory.getLogger(CakeServiceImpl.class);

	/**
	 * A method to create a cake with the given cakDTO
	 * @Param cakeDTO
	 * @return CakeDTO
	 */
	public CakeDTO createCake(CakeDTO cakeDTO) {

		logger.debug("Validating cake details before creating");
		validator.validateInput(cakeDTO);
		logger.debug("validation successful, creating a cake");

		Cake cake = Cake.builder()
				    .description(cakeDTO.getDescription())
				    .title(cakeDTO.getTitle())
				    .image(cakeDTO.getImage())
				    .build();

		Cake cakeCreated = cakeRepository.save(cake);

		return CakeDTO.builder()
				.description(cakeCreated.getDescription())
				.title(cakeCreated.getTitle())
				.image(cakeCreated.getImage())
				.build();
	}

	/**
	 * A method to retrieve all elements from cakes collection
	 * @return List<CakeDTO>
	 */
	public List<CakeDTO> getAllCakes() {
		logger.debug("finding all cakes");
		List<Cake> cakes = cakeRepository.findAll();
		List<CakeDTO> cakeDTOS = new ArrayList<>();
		for (Cake cake : cakes) {
			cakeDTOS.add(CakeDTO.builder()
					.description(cake.getDescription())
					.title(cake.getTitle())
					.image(cake.getImage())
					.build());
		}
		return cakeDTOS;
	}

}
