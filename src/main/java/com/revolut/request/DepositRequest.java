package com.revolut.request;

import java.math.BigDecimal;

public class DepositRequest {

	private String IBAN;
	private BigDecimal amount;

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
