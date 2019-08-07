package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;

public class AccountDAOImpl implements AccountDAO {

	static Map<Long,Account> accmap = new HashMap<Long,Account>();
	
	
	@Override
	public boolean addAccount(Account ob) {
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connected");
			
			con.setAutoCommit(false);
			
			
			String sqlQuery="insert into account values(?,?,?,?)";
			
			PreparedStatement st=con.prepareStatement(sqlQuery);
			st.setInt(1, ob.getAid());
			st.setLong(2, ob.getMobile());
			st.setString(3, ob.getAccountholder());
			st.setDouble(4, ob.getBalance());
			
			int insertedRec=st.executeUpdate();
			System.out.println("Inserted Records "+ insertedRec);
			con.commit();
			con.close();
			
			
			
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage()+" "+e.getErrorCode()+" "+e.getSQLState());
			e.printStackTrace();
		}
		
		
		
	    return true;
		
	}

	@Override
	public boolean updateAccount(Account ob) throws SQLException {
		// TODO Auto-generated method stub
        
        Connection con=null;
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
		
		//Lode the driver
		try {
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());////java 8 automatically load driver
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			 con=DriverManager.getConnection(url,user,pass);
			System.out.println("Your are connected");
			
			con.setAutoCommit(false);///tells that do not commit after every statement
			
			
			selectSt=con.prepareStatement("select * from account where aid=?");
			selectSt.setInt(1,ob.getAid());
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0;
			long mb1=0L;
			String ah1="";
			System.out.println("Details of first Account===");
			if(rs1!=null) {
				if(rs1.next()) {
					mb1=rs1.getLong("mobileno");
					ah1=rs1.getString(3);
							
					System.out.println(ah1);
					bal1=rs1.getDouble("balance");
					System.out.println("Your Balance :- "+bal1);
					
				}
			}
			//System.out.println("Enter reciver Account ID");
			//int id1=sc.nextInt();
			
			
			
			
			updateSt=con.prepareStatement("update account set mobileno=?,accountholder=?,balance=? where aid=?");
			updateSt.setLong(1,ob.getMobile());
			updateSt.setString(2,ob.getAccountholder());
			updateSt.setDouble(3,ob.getBalance());
			updateSt.setInt(4,ob.getAid());
			int i1=updateSt.executeUpdate();
			System.out.println("Account updated"+i1);
			//System.out.println("hello");
			
			
			
			
		
			
			con.commit();
		
			return true;
		}
		catch(SQLException e) {
			con.rollback();
			System.out.println(e.getMessage()+""+e.getErrorCode()+""+e.getSQLState());
			e.printStackTrace();
		}
		finally {
			//System.out.println("Connection Closed");
			if(con!=null)con.close();
			
		}
		//return from.getBalance();

		return false;
	}

	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
		
		////Load the driver
		try {
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());////java 8 automatically load driver
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			 con=DriverManager.getConnection(url,user,pass);
			System.out.println("Your are connected");
			
			con.setAutoCommit(false);///tells that do not commit after every statement
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter you Account ID");
			int id=sc.nextInt();
			selectSt=con.prepareStatement("delete from account where aid=?");
			selectSt.setInt(1,ob.getAid());
			int i=selectSt.executeUpdate();
			System.out.println("Deleted "+i);
			con.commit();
		
		}
		catch(SQLException e) {
			//con.rollback();
			System.out.println(e.getMessage()+""+e.getErrorCode()+""+e.getSQLState());
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public Account findAccount(Long mobileno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
		
		//Lode the driver
		try {
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());////java 8 automatically load driver
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			 con=DriverManager.getConnection(url,user,pass);
		//	System.out.println("Your are connected");
			
			con.setAutoCommit(false);///tells that do not commit after every statement
			
						
			selectSt=con.prepareStatement("select * from account where mobileno=?");
			selectSt.setLong(1,mobileno);
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0;
			int aid=0;
			long mb1=0L;
			String ah1="";
			if(rs1!=null) {
				if(rs1.next()) {
					
				}
				    aid=rs1.getInt("aid");
					
					ah1=rs1.getString("accountholder");
					bal1=rs1.getDouble("balance");
					
					
				}
			Account ob= new Account(aid,mobileno,ah1,bal1);
			return ob;
			}
		catch(Exception e)
		{
			
		}
		Account ob = new Account();
		return ob;
	}

	@Override
	public Map<Long, Account> getAllAccounts() throws SQLException {
		// TODO Auto-generated method stub
		try
		{
		   
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e)
		{
		 e.printStackTrace();	
		}

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String pass="hr";
		
		Connection con=DriverManager.getConnection(url,user,pass);
		System.out.println("Connected");
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//read only....changes are not reflected in result set.
		ResultSet rs=st.executeQuery("select * from account");
		while(rs.next())
		{
			int a_id=rs.getInt("aid");
			long mobile=rs.getLong("mobileno");
			String ah=rs.getString(3);
			double bal=rs.getDouble(4);
			
			Account ob=new Account(a_id,mobile,ah,bal);
			accmap.put(mobile, ob);
			
		}
		
		
		
		return accmap;
	}

	@Override
	public double TransferMoney(Account from, Account to, double amount) throws InsuffecientFundException, SQLException {
		// TODO Auto-generated method stub
		
		
		
		Connection con=null;
		PreparedStatement updateSt=null;
		PreparedStatement selectSt=null;
		
		//Lode the driver
		try {
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());////java 8 automatically load driver
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			 con=DriverManager.getConnection(url,user,pass);
			System.out.println("Your are connected");
			
			con.setAutoCommit(false);///tells that do not commit after every statement
			
			
			selectSt=con.prepareStatement("select * from account where aid=?");
			selectSt.setInt(1,from.getAid());
			ResultSet rs1=selectSt.executeQuery();
			double bal1=0.0;
			long mb1=0L;
			String ah1="";
			System.out.println("Details of first Account===");
			if(rs1!=null) {
				if(rs1.next()) {
					mb1=rs1.getLong("mobileno");
					ah1=rs1.getString(3);
							
					System.out.println(ah1);
					bal1=rs1.getDouble("balance");
					System.out.println("Your Balance :- "+bal1);
					
				}
			}
			//System.out.println("Enter reciver Account ID");
			//int id1=sc.nextInt();
			
			System.out.println("Details of first Account===");
			selectSt.setInt(1,to.getAid());
			ResultSet rs2=selectSt.executeQuery();
			double bal2=0.0;
			long mb2=0L;
			String ah2="";
			if(rs2!=null) {
				if(rs2.next()) {
					mb2=rs2.getLong("mobileno");
					ah2=rs2.getString(3);
							
					System.out.println(ah2);
					bal2=rs2.getDouble("balance");
					System.out.println("Your Balance :- "+bal2);
					
				}
			}
			
			
			updateSt=con.prepareStatement("update account set mobileno=?,accountholder=?,balance=? where aid=?");
			updateSt.setLong(1,mb1);
			updateSt.setString(2,ah1);
			updateSt.setDouble(3,bal1-amount);
			updateSt.setInt(4,from.getAid());
			int i1=updateSt.executeUpdate();
			System.out.println("Account updated"+i1);
			//System.out.println("hello");
			
			
			updateSt.setLong(1,mb2);
			updateSt.setString(2,ah2);
			updateSt.setDouble(3,bal2+amount);
			updateSt.setInt(4,to.getAid());
			 i1+=updateSt.executeUpdate();
			// System.out.println("HII");
			System.out.println("Account updated"+i1);
			
		
			
			con.commit();
		
			return bal1-amount;
		}
		catch(SQLException e) {
			con.rollback();
			System.out.println(e.getMessage()+""+e.getErrorCode()+""+e.getSQLState());
			e.printStackTrace();
		}
		finally {
			//System.out.println("Connection Closed");
			if(con!=null)con.close();
			
		}
		return from.getBalance();

	}
	
	
	
	

}
