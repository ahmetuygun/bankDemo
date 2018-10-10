package com.revolut.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.revolut.dao.AccountDao;
import com.revolut.model.Account;
import com.revolut.request.CreateAccountRequest;
import com.revolut.request.DepositRequest;
import com.revolut.request.TransferRequest;
import com.revolut.request.WithdrawRequest;

public class AccountService {

	private static class AccountServiceHolder {
		public static final AccountService instance = new AccountService();
	}

	public static AccountService getInstance() {
		return AccountServiceHolder.instance;
	}

	public Account createAccount(CreateAccountRequest createAccountRequest) {

		long digit16 = (long) (Math.random() * 10000000000000000L);

		Account account = new Account();
		
		account.setIBAN(String.valueOf(digit16));
		account.setName(createAccountRequest.getName());
		account.setBalance(createAccountRequest.getInitialAmount());
		AccountDao.getInstance().createAccount(account);
		
		return account;

	}

	public Account deposit(DepositRequest depositRequest) throws Exception {
		 
		Account account = AccountDao.getInstance().getAccountByIban(depositRequest.getIBAN());
		if(account!=null) {
			account.setBalance(account.getBalance().add(depositRequest.getAmount()) );
			AccountDao.getInstance().updateAccount(account);

		}else {
			
			throw new Exception("There is no account such that");
		}
		

		return account;
	}

	public Account withdraw(WithdrawRequest withdrawRequest) throws Exception {
		Account account = AccountDao.getInstance().getAccountByIban(withdrawRequest.getIBAN());
		if(account!=null) {
			BigDecimal diff = account.getBalance().subtract(withdrawRequest.getAmount());
			if(diff.compareTo(BigDecimal.ZERO) < 0){
				throw new Exception("insufficient balance");
			}
			account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));
			AccountDao.getInstance().updateAccount(account);

		}else {
			
			throw new Exception("There is no account such that");
		}
		

		return account;
	}

	public List<Account> transfer(TransferRequest transferRequest) throws Exception {

		
		Account sender = AccountDao.getInstance().getAccountByIban(transferRequest.getSender());

		if(sender==null) {
			throw new Exception("Missing or invalid sender! ");
		}
		
		Account receiver = AccountDao.getInstance().getAccountByIban(transferRequest.getReceiver());

		if(receiver==null) {
			throw new Exception("Missing or invalid receiver! ");
		}
		
		BigDecimal diff = sender.getBalance().subtract(transferRequest.getAmount());
		if(diff.compareTo(BigDecimal.ZERO) < 0){
			throw new Exception("insufficient balance");
		}
		sender.setBalance(sender.getBalance().subtract(transferRequest.getAmount()));

		receiver.setBalance(sender.getBalance().add(transferRequest.getAmount()) );

		AccountDao.getInstance().updateAccount(sender);
		AccountDao.getInstance().updateAccount(receiver);
		List<Account> list = new ArrayList<Account>();
		list.add(sender);
		list.add(receiver);
		return list;
	}

}
