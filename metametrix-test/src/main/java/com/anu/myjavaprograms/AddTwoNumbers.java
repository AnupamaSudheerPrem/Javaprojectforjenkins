package com.anu.myjavaprograms;

import java.util.Scanner;

public class AddTwoNumbers {

	public static void main1(String[] args) {
		int a=2;
		int b=7;
		int sum = a +b;
		System.out.println(sum);

	}
	
	public static void main2(String[] args) {
		
		int number ;
		System.out.println("Enter a number");
		
		Scanner sc = new Scanner(System.in);
		number = sc.nextInt();
		if(number%2==0) {
			System.out.println("the nuber is even:"+number);
		}
			else  
			{
				System.out.println("the number is odd:"+number);
			}
				
		
		
	}
	
	public static void main(String[] args) {
		
	}

}
