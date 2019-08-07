package cg;

public class Calculator {
	
	public int add(int a,int b)
	{
		int res=0;
		if(a>=0&&b>=0)
			res=a+b;
		return res;
	}
	
//	public int sub(int a,int b)
//	{
//		int res=0;
//		res=a-b;
//		return res;
//	}
	
	public double getId()
	{
		System.out.println((int)(Math.random()*1000));
		return (int)(Math.random()*1000);
	}
	

}
