package com.waracle.cakemanager.exception;

import java.io.Serializable;

public class CakeException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 5493040836973293034L;
	private String errorMessage;

	public CakeException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
