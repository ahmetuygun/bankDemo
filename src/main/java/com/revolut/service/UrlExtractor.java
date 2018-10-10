package com.revolut.service;

import java.math.BigDecimal;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.revolut.request.CreateAccountRequest;
import com.revolut.request.DepositRequest;
import com.revolut.request.TransferRequest;
import com.revolut.request.WithdrawRequest;

public class UrlExtractor {

	public static CreateAccountRequest exractCreateAccountRequest(UriInfo uriInfo) throws Exception {

		CreateAccountRequest createAccountRequest = new CreateAccountRequest();

		final MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		if (queryParameters.containsKey("fullName") && !"".equals(queryParameters.get("fullName"))) {

			createAccountRequest.setName(queryParameters.get("fullName").toString());

		} else {
			throw new Exception("Full Name couldnt be empty!");
		}
		if (queryParameters.containsKey("initAmount")) {

			try {
				new BigDecimal(queryParameters.get("initAmount").get(0));
			} catch (Exception e) {
				throw new Exception("Access valid digit for initial amount");
			}
			createAccountRequest.setInitialAmount(new BigDecimal(queryParameters.get("initAmount").get(0)));

		} else {
			throw new Exception("Initial amount couldnt be empty!");

		}

		return createAccountRequest;

	}

	public static DepositRequest exractDepositRequest(UriInfo uriInfo) throws Exception {

		DepositRequest depositRequest = new DepositRequest();

		final MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		if (queryParameters.containsKey("iban") && !"".equals(queryParameters.get("iban"))) {
			depositRequest.setIBAN(queryParameters.get("iban").get(0));
		} else {

		}

		if (queryParameters.containsKey("amount")) {

			try {
				new BigDecimal(queryParameters.get("amount").get(0));
			} catch (Exception e) {
				throw new Exception("Access valid digit for initial amount");
			}
			depositRequest.setAmount(new BigDecimal(queryParameters.get("amount").get(0)));

		} else {
			throw new Exception("Initial amount couldnt be empty!");

		}

		// TODO Auto-generated method stub
		return depositRequest;
	}

	public static WithdrawRequest exractWithdrawRequest(UriInfo uriInfo) throws Exception {

		WithdrawRequest withdrawRequest = new WithdrawRequest();

		final MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		if (queryParameters.containsKey("iban") && !"".equals(queryParameters.get("iban"))) {
			withdrawRequest.setIBAN(queryParameters.get("iban").get(0));
		} else {

		}

		if (queryParameters.containsKey("amount")) {

			try {
				new BigDecimal(queryParameters.get("amount").get(0));
			} catch (Exception e) {
				throw new Exception("Access valid digit for initial amount");
			}
			withdrawRequest.setAmount(new BigDecimal(queryParameters.get("amount").get(0)));

		} else {
			throw new Exception("Initial amount couldnt be empty!");

		}

		// TODO Auto-generated method stub
		return withdrawRequest;

	}

	public static TransferRequest exractTransferRequest(UriInfo uriInfo) throws Exception {

		TransferRequest transferRequest = new TransferRequest();
		final MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

		if (queryParameters.containsKey("sender") && !"".equals(queryParameters.get("sender"))) {
			transferRequest.setSender(queryParameters.get("sender").get(0));
		} else {
			throw new Exception("Missing or invalid  sender!");
		}

		if (queryParameters.containsKey("receiver") && !"".equals(queryParameters.get("receiver"))) {
			transferRequest.setReceiver(queryParameters.get("receiver").get(0));
		} else {
			throw new Exception("Missing or invalid  receiver!");
		}
		
		if (queryParameters.containsKey("amount")) {

			try {
				new BigDecimal(queryParameters.get("amount").get(0));
			} catch (Exception e) {
				throw new Exception("Access valid digit for initial amount");
			}
			transferRequest.setAmount(new BigDecimal(queryParameters.get("amount").get(0)));

		} else {
			throw new Exception("Initial amount couldnt be empty!");

		}

		return transferRequest;
	}

}
