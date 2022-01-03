package com.waracle.cakemanager.utility;

import com.waracle.cakemanager.exception.CakeException;
import com.waracle.cakemanager.model.CakeDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Validator {

	public void validateInput(CakeDTO cakeDTO) {

		if (cakeDTO.getDescription() == null
				|| StringUtils.isEmpty(cakeDTO.getDescription())) {
			throw new CakeException(ApplicationResources.EMPTY_DESCRIPTION);
		} else if (cakeDTO.getImage() == null
				|| StringUtils.isEmpty(cakeDTO.getImage())) {
			throw new CakeException(ApplicationResources.EMPTY_IMAGE);
		} else if (cakeDTO.getTitle() == null
				|| StringUtils.isEmpty(cakeDTO.getTitle())) {
			throw new CakeException(ApplicationResources.EMPTY_TITLE);
		}

	}

}
