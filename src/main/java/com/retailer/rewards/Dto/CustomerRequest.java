package com.retailer.rewards.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
	
	@NotBlank(message = "Name must not be blank")
	private String name;

}
