/*
ID: orange.3
PROG: sprime
LANG: JAVA
 */

//GIT TEST ABCDEFGHIJKLMNOPQRSTUVWXYZ
//LORUM IPSUM DOLOR
//GIT TEST
//

import java.util.*;
import java.io.*;

class sprime 
{
	public static TreeSet<Integer> answer = new TreeSet<Integer>();
	public static void main(String[] args) throws IOException
	{
		BufferedReader buff_in = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		
		int length = Integer.parseInt(buff_in.readLine());
		calc(2, 1, length, answer);
		calc(3, 1, length, answer);
		calc(5, 1, length, answer);
		calc(7, 1, length, answer);
		for(int x : answer)
		{
			output.println(x);
		}
		output.close();
	}
	
	static boolean prime(int n) 
	{
		if(n <= 1)
		{
			return false;
		}
		if(n <= 3)
		{
			return true;
		}
		if(n % 2 == 0 || n % 3 == 0)
		{
			return false;
		}
		for(int x = 5; x * x <= n; x += 6)
		{
			if(n % x == 0 || n % (x + 2) == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	static void calc(int num, int digits, int length, TreeSet<Integer> answer)
	{
		if(digits == length)
		{
			answer.add(num);
		}
		
		int newnum = num * 10;
		if(prime(newnum + 1))
		{
			 calc(newnum + 1, digits + 1, length, answer);
		}
		if(prime(newnum + 3))
		{
			calc(newnum + 3, digits + 1, length, answer);
		}
		if(prime(newnum + 7))
		{
			calc(newnum + 7, digits + 1, length, answer);
		}
		if(prime(newnum + 9))
		{
			calc(newnum + 9, digits + 1, length, answer);
		}
	}
}
