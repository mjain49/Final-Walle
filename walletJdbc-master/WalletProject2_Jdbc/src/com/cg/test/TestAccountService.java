package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.cg.bean.*;
import com.cg.exception.InsuffecientFundException;
import com.cg.service.AccountService;



class TestAccountService{
		 static Account a;
		 static Account c;
		 
		 static AccountService tac;
		 
		 @BeforeAll
		 public static void beforeAllTests()
			{
				System.out.println("Before All Test");
				tac=new AccountService();
				a= new Account(108,1234567889,"Mjakksa",90000.00);
			}
			
		 @AfterAll
		 public static void afterAllTests()
		 {
			 System.out.println("After All Test");
			 tac=null;
			 a=null;
		 }
		
		 @Test
		 public void TestAddAccount()throws InsuffecientFundException, SQLException
		 {
			 assertTrue(tac.addAccount(a));
			 
		 }
		 
		 
		 @Test
		 public void TestDeleteAccount() throws InsuffecientFundException, SQLException
		 {
			 tac.addAccount(a);
			 assertTrue(tac.deleteAccount(a));
			 
		 }
		 
		 @Test
		 public void Testwithdraw1() throws InsuffecientFundException, SQLException
		 {
			 double amount=5000;
			 tac.addAccount(a);
			 
		     assertEquals(85000,tac.withdraw(a, amount));
		 }
		 
		 @Test 
		 public void Testwithdraw2() throws InsuffecientFundException, SQLException
		 {
			 double amount=10000;
			 tac.addAccount(a);
			 assumeFalse(tac.findAccount(a.getMobile())==null);
			 assertEquals(75000,tac.withdraw(a, amount));
			 
		 }

		 @Test
		 public void TestfindAccount()throws InsuffecientFundException, SQLException
		 {
			 tac.addAccount(a);
			 Account b=tac.findAccount(a.getMobile());
			 assertEquals(b.getMobile(), a.getMobile());
			 assertEquals(b.getAccountholder(), a.getAccountholder());
			 assertEquals(b.getBalance(), a.getBalance());
			 assertEquals(b.getAid(), a.getAid());
			 System.out.println("Done");
			 
		 }		
		 
		 
		 
		 @Test
		 public void TestTransferMoney()throws InsuffecientFundException, SQLException
		 {
			 tac.addAccount(a);
			 if(tac.findAccount(c.getMobile())!=null);
			 	tac.deleteAccount(c);
			 c=new Account(109,1111111111,"Account109",20000);
			 tac.addAccount(c);
			 double amount=10000;
			 tac.TransferMoney(a, c, amount);
			 assertEquals(80000,a.getBalance());
			 assertEquals(30000, c.getBalance()); 
			 
		 }
		 
		 

}