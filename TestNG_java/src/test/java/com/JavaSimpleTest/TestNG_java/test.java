package com.JavaSimpleTest.TestNG_java;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("message");
		int[] myarray = {10,83,1,25,4,5};

		int nextvalue=0;
		for	(int i=0; i < myarray.length;i++){
			int value = myarray[i];
			if(value> nextvalue){
				nextvalue=myarray[i];
			}
		}
		System.out.println("the max value is "+ nextvalue);

	}

}
