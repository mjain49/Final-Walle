package com.pg.pl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cg.bean.*;
import com.cg.exception.InsuffecientFundException;
import com.cg.service.*;

public class MyWallet {
   
	public static void main(String args[]) throws IOException, SQLException
	{
		
		  int id=0;
		  long mb =0L;
		  long mb1=0L;
		  String ah="";
		  double bal =0.0;
		  AccountService service = new AccountService();
		  AccountService service1 = new AccountService();
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  String choice="";
		while(true)
		{
		System.out.println("Menu");
		System.out.println("===========");
		System.out.println("1 Create New Account");
		System.out.println("2 Print All Accounts");
		System.out.println("3 Witdraw Money");
		System.out.println("4 Deposit Money");
		System.out.println("5 Transfer Money");
		System.out.println("6 Exit");
		System.out.println("Enter your choice");
		choice=br.readLine();
		
		switch(choice)
		{
		case "1":
				  
				  
				  //Accepting and validating input for account number.......
				  System.out.println("Enter account number");
				  while(true)
				  {
					  String s_id=br.readLine();
					  boolean ch1=Validator.validatedata(s_id, Validator.aidpattern);
					  if(ch1==true)
					  {
						  try {
							  id=Integer.parseInt(s_id);
							  break;
						  }
						  catch(NumberFormatException e)
					  
					  {
						 System.out.println("Account Number must be numeric. Re Enter"); 
					  }
				  }
					  else
					  {
						  System.out.println("Re Enter Account Number in 3 digits");
					  }
				  
				  }//end of while
				  //Accepting and validating input for mobile number
				  System.out.println("Enter mobile number");
				  while(true)
				  {
					  String s_mb=br.readLine();
					  boolean ch1=Validator.validatedata(s_mb, Validator.mobilepattern);
					  if(ch1==true)
					  {
						  try {
							  mb=Long.parseLong(s_mb);
							  break;
						  }
						  catch(NumberFormatException e)
					  
					  {
						 System.out.println("Mobile Number must be numeric. Re Enter"); 
					  }
				  }
					  else
					  {
						  System.out.println("Re Enter Mobile Number in 10 digits");
					  }
				  
				  }//end of mobile validation
				  
				  //accepting and validating account holder
				  System.out.println("Enter Account holder");
				 while(true)
				  {
					  ah=br.readLine();
					  boolean ch1=Validator.validatedata(ah, Validator.namepattern);
					  if(ch1==false)
					  {
						  
						  System.out.println("Re Enter Name"); 
						  
				       }
					  else
						  break;
					  
				  
				  }//end of mobile validation
				  
				  
				  // accepting and validating balance
				 System.out.println("Enter Balance");
				 while(true)
				  {
					 String s_bal=br.readLine();
					  boolean ch1=Validator.validatedata(s_bal, Validator.balancepattern);
					  if(ch1==true)
					  {
						  try {
							  bal= Double.parseDouble(s_bal);
							  break;
						  }
						  catch(NumberFormatException e)
					  
					  {
						 System.out.println("Balance must be numeric. Re Enter"); 
					  }
				  }
					  else
					  {
						  System.out.println("Re Enter Balance");
					  }
				  
				  }
				 
				  
				 
				  
				  Account ob= new Account(id,mb,ah,bal);
				 
			      service.addAccount(ob);
				  System.out.println("Successfully Added");
				  
				  break;
		     	
	    case "2"://print all accounts
	    	     
	    	     Map <Long,Account> accmap=service.getAllAccounts();
	    	     Collection<Account> vc = accmap.values();
	    	     List<Account> acclist=new ArrayList<Account>(vc);
	    	     Collections.sort(acclist);
	    	     for(Account o : acclist)
	    	     {
	    	    	 
	    	    	 service.printStatment(o);
	    	    	 
	    	     }
			     break;
	    case "3":
	    	 
			  //--------------------- withdraw money
	    	 //---------------taking mobile no. from which you want to deposit

			  System.out.println("Enter mobile number to withdraw");
			  while(true)
			  {
				  String s_mb=br.readLine();
				  boolean ch1=Validator.validatedata(s_mb, Validator.mobilepattern);
				  if(ch1==true)
				  {
					  try {
						  mb=Long.parseLong(s_mb);
						  break;
					  }
					  catch(NumberFormatException e)
				  
				  {
					 System.out.println("Mobile Number must be numeric. Re Enter"); 
				  }
			  }
				  else
				  {
					  System.out.println("Re Enter Mobile Number in 10 digits");
				  }
			  
			  }//end of while
			  			  
			  //on basis of mobile number finding account
			  Account ob1=  service.findAccount(mb);
			  System.out.println("=============================================");
			  System.out.println("Current Balance of Account is "+ob1.getBalance());
			  System.out.println("=============================================");
			  //Amount to be withdraw
			  System.out.println("Enter amount to withdraw");
			  while(true)
			  {
				  String s_bal1=br.readLine();
				  bal= Double.parseDouble(s_bal1);
				  if(bal>0)
				  {
					  
						  break;
					  
			  }
				  else
				  {
					  System.out.println("Invalid Amount...Please, Re Enter Amount");
				  }
			  
			  }
			  
			  double b1= 0.0;
			try
			{
			 b1=service.withdraw(ob1, bal);
			System.out.println("After Withdraw Balance is "+b1);
			System.out.println("=============================================");
			System.out.println(" ");
			service.updateAccount(ob1);
			
			}
			catch(InsuffecientFundException e)
			{
				System.err.println(e.getMessage());
				System.err.println(e);
				
			}
		     break;
	    
	    case "4":
	    	//Deposit
	    	
	    	//------------------------------mobile number to deposit
	    	System.out.println("Enter mobile number to deposit");
			  while(true)
			  {
				  String s_mb1=br.readLine();
				  boolean ch1=Validator.validatedata(s_mb1, Validator.mobilepattern);
				  if(ch1==true)
				  {
					  try {
						  mb=Long.parseLong(s_mb1);
						  break;
					  }
					  catch(NumberFormatException e)
				  
				  {
					 System.out.println("Mobile Number must be numeric. Re Enter"); 
				  }
			  }
				  else
				  {
					  System.out.println("Re Enter Mobile Number in 10 digits");
				  }
			  
			  }
	    	  
			  Account ob2=  service.findAccount(mb);
			  System.out.println("=============================================");
			  System.out.println("Current Balance of Account is "+ob2.getBalance());
			  System.out.println("=============================================");
			  
			  //--------------------------------------------------------amount to be deposit
			  System.out.println("Enter amount to deposit");
			  
			  
			  while(true)
			  {
				  String s_bal2=br.readLine();
				  bal= Double.parseDouble(s_bal2);
				  if(bal>0)
				  {
					  
						  break;
					  
			  }
				  else
				  {
					  System.out.println("Invalid Amount.....Please, Re Enter Amount");
				  }
			  
			  }
			  
			  double b2= 0.0;
			try
			{
			 b2=service.Deposit(ob2, bal);
			System.out.println("After Deposit balance is "+b2);
			System.out.println("=============================================");
			System.out.println(" ");
			service.updateAccount(ob2);
			
			}
			catch(InsuffecientFundException e)
			{
				System.err.println(e.getMessage());
				//System.err.println(e);
				
			}
		     break;
	    
	    case "5":
	    	// transfer
	    	
	    	//------------------------------------------------mobile number of from account
	    	System.out.println("Enter mobile number from which you want to withdraw");
	    	 while(true)
			  {
	    		 String s_mb3=br.readLine();
				  boolean ch1=Validator.validatedata(s_mb3, Validator.mobilepattern);
				  if(ch1==true)
				  {
					  try {
						  mb=Long.parseLong(s_mb3);
						  break;
					  }
					  catch(NumberFormatException e)
				  
				  {
					 System.out.println("Mobile Number must be numeric. Re Enter"); 
				  }
			  }
				  else
				  {
					  System.out.println("Re Enter Mobile Number in 10 digits");
				  }
			  
			  } 
	    	  Account ob3=  service.findAccount(mb);
	    	  System.out.println("=============================================");
			  System.out.println("Current Balance of Account is "+ob3.getBalance());
			  System.out.println("=============================================");
	    	
			  //--------------------------------------------------mobile number of to account
			  System.out.println("Enter mobile number to which you want to deposit");
			  while(true)
			  {
				  String s_mb4=br.readLine();
				  boolean ch1=Validator.validatedata(s_mb4, Validator.mobilepattern);
				  if(ch1==true)
				  {
					  try {
						  mb1=Long.parseLong(s_mb4);
						  
						  
						  break;
					  }
					  catch(NumberFormatException e)
				  
				  {
					 System.out.println("Mobile Number must be numeric. Re Enter"); 
				  }
			  }
				  else
				  {
					  System.out.println("Re Enter Mobile Number in 10 digits");
				  }
			  
			  } 
			  Account ob4=  service1.findAccount(mb1);
			  System.out.println("=============================================");
			  System.out.println("Current Balance of Account is "+ob4.getBalance());
			  System.out.println("=============================================");
			  
			  
			 
			 
			  // ----------------------------------------Enter amount to be transferred
			  
			  
			  
			  
			  System.out.println("Enter amount to transfer ");
			  while(true)
			  {
				  String s_bal3=br.readLine();
				  bal= Double.parseDouble(s_bal3);
				  if(bal>0)
				  {
					  
						  break;
					  
			  }
				  else
				  {
					  System.out.println("Invalid Amount...Please, Re Enter Amount");
				  }
			  
			  }
			  
			  
			  
			  double b3= 0.0;
			try
			{
			 b3=service.TransferMoney(ob3, ob4, bal);
			 
			System.out.println("After transfer new balance of First Account is  "+b3);
			System.out.println("=============================================");
			System.out.println(" ");
			service.updateAccount(ob3);
			service1.updateAccount(ob4);
			
			}
			catch(InsuffecientFundException e)
			{
				System.err.println(e.getMessage());
				//System.err.println(e);
				
			}
		     break;
		     
		case "6":
			     System.out.println("Exiting Program");
			     System.exit(0);
			     break;
	    default:
	    	    System.out.println("Invalid choice");
		
		
		
		
		
		}
		
		
		
		
		
		
		
		
		}
		
	}
	
}
