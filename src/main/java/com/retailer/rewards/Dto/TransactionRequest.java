package com.retailer.rewards.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
	
	@NotNull(message = "Amount is required")
	@Min(value = 1, message = "Amount must be atleast 1")
	private Double amount;
	
	@NotNull(message = "Data is required")
	private LocalDate date;
	
	@NotNull(message = "Customer ID is required")
	private Long customerId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}

