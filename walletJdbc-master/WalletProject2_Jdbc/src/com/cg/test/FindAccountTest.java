package com.cg.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.bean.Account;
import com.cg.dao.AccountDAOImpl;
import com.cg.service.AccountService;

class FindAccountTest {
	
	AccountService as;
	AccountDAOImpl adi;
	
	
	@BeforeEach
	public void setup()
	{
		as=new AccountService();
		adi=EasyMock.createMock(AccountDAOImpl.class);
		as.setAccountDAOImpl(adi);
	}
	

	@Test
	void test() {
		long mobileno=123456789L;
		
		Account ac=new Account(111,123456789L,"Abc Def",40500);
		
		EasyMock.expect
		(adi.findAccount(mobileno)).andReturn(ac);
		
		EasyMock.replay(adi);
		
		assertEquals(ac,as.findAccount(mobileno));
		
		EasyMock.verify(adi);
		
	}

}
