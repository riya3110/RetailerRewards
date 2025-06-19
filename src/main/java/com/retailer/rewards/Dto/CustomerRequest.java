package com.retailer.rewards.Dto;

import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {
	
	@NotBlank(message = "Name must not be blank")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
