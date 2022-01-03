package com.waracle.cakemanager.controller;

import com.waracle.cakemanager.model.CakeDTO;
import com.waracle.cakemanager.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CakeController {

	@Autowired
	private final CakeService cakeService;
	final Logger logger = LoggerFactory.getLogger(CakeController.class);

	@PostMapping(value = "/cake")
	public ResponseEntity<CakeDTO> createCake(
			@RequestBody CakeDTO cakeDTO) {
		logger.debug("Creating a cake");
		return new ResponseEntity<>(cakeService.createCake(cakeDTO),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/cakes")
	public ResponseEntity<List<CakeDTO>> getAllCakes() {
		logger.debug("Retrieving cakes");
		return new ResponseEntity<>(cakeService.getAllCakes(), HttpStatus.OK);
	}

}
