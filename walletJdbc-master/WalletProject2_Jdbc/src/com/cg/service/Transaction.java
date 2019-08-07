package com.cg.service;
import java.sql.SQLException;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;
public interface Transaction extends AccountOperation{
	
	public double withdraw(Account ob, double amount) throws InsuffecientFundException, SQLException;
	public double Deposit(Account ob, double amount) throws InsuffecientFundException, SQLException; 
	public double TransferMoney(Account from, Account to,double amount) throws InsuffecientFundException, SQLException; 
	
	public default void printStatment(Account ob)
	{
		System.out.println("=====================================");
		System.out.println("Statement for Account No. :- "+ob.getAid());
		System.out.println("Mobile No. :- "+ob.getMobile());
		System.out.println("AccountHolder Name :- "+ob.getAccountholder());
		System.out.println("Balance is :- "+ob.getBalance());
		System.out.println("=====================================");
	}

}
