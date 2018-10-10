package com.revolut.request;

import java.math.BigDecimal;

public class CreateAccountRequest {

	private String name;
	private BigDecimal initialAmount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

}
