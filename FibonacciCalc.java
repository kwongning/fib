///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  FibonacciCalc.java
// File:             FibonacciCalc.java
// Date:         	 June 2017
// Authors:			 Vincent W.
// Reference:		 Lucas's Formula - http://mathforum.org/library/drmath/view/52686.html
//
// This program demostrated how to slove Fibonacci number "F(n) = F(n-1) + F(n-2)" in different ways. 
// If you face java stack overflow exception, you may need to add -Xss512m  or larger to set the maximum stack size of Java VM.
// If you face java out of memory error, you may need to add -Xms8g or larger to set the maximum heap memory size of Java VM.
//
// Basic algorithm to slove the Fibonacci number "F(n) = F(n-1) + F(n-2)"
// By definition, "F(n) = F(n-1) + F(n-2)", "F(1) = 1" and "F(0) = 0"
//
// Recursive Method is a method to break down the big problem into small problems, and then slove it.
// This method is straight forward TopDown methodand easy to understand.
// However, this method require a lot of processing power due to many duplicate function call.
// We need an alternate approach to slove this problem.
// 
// Memoization - use memory lookup the stored result before making another function call.
// Relation - find out the problems relation or formula to calculate the result directly by using the formula.
// Iteration - convert the recursion into interation for calculation.
// Dynamic Programming - combine the memoization and iteration or relation to save processing time, also increase the resability.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.math.*;
import java.util.Scanner;


/**
 * Slove Fibonacci number "F(n) = F(n-1) + F(n-2)" in different ways.
 *
 * @authors 	Vincent W. Copyright (2017)
 * @version		1.0
 * @since		June 2017
 */

public class FibonacciCalc
{
	/**
     * Data Structure for Memoization.
     */
	ArrayList<BigInteger> mrArrayList;
	Map<Integer, BigInteger> mrHashMap;
	ArrayList<BigInteger> imArrayList;


    /**
     * Constructor
     * init: Data Structure for Memoization.
     */
    FibonacciCalc() {
    	this.imArrayList = new ArrayList<BigInteger>();
    	this.imArrayList.add(BigInteger.ZERO);
    	this.imArrayList.add(BigInteger.ONE);
    	this.mrArrayList = new ArrayList<BigInteger>();
    	this.mrArrayList.add(BigInteger.ZERO);
    	this.mrArrayList.add(BigInteger.ONE);
    	this.mrHashMap = new HashMap<Integer, BigInteger>();
    	this.mrHashMap.put(0, BigInteger.ZERO);
    	this.mrHashMap.put(1, BigInteger.ONE);
    }

    /**
	 * Recursive Method
     * Returns the value of Fibonacci number.
     * 
     * @param the number of Fibonacci number need to compute.
     *
     * @return the value of Fibonacci number.
     */
    BigInteger rFibonacci(int n)  {
    	if (n < 2) 
    		return new BigInteger(Integer.toString(n));
    	else
    		return rFibonacci(n - 1).add(rFibonacci(n - 2));
    }

    /**
	 * Dynamic Programming - Recursive with Memoization Array (Top to Bottom)
	 * 
     * Returns the value of Fibonacci number.
     * 
     * @param the number of Fibonacci number need to compute.
     *
     * @return the value of Fibonacci number.
     */
    BigInteger mrFibonacci(int n) {
		//performing datalookup
    	if (n < this.mrArrayList.size()){
    		return this.mrArrayList.get(n);
    	} else {
    		BigInteger currentValue = mrFibonacci(n - 1).add(mrFibonacci(n - 2));
			//performing memoization
    		this.mrArrayList.add(currentValue);
    		return currentValue;
    	}
    }

    /**
	 * Dynamic Programming - Recursive with Memoization HashMap (Top to Bottom)
	 * 
     * Returns the value of Fibonacci number.
     * 
     * @param the number of Fibonacci number need to compute.
     *
     * @return the value of Fibonacci number.
     */
    BigInteger mrMapFibonacci(int n) {
    	if (this.mrHashMap.containsKey(n)) {
    	//Debug Line
		//System.out.println("f(" + n + ")= " + this.mrHashMap.get(n));
    		return this.mrHashMap.get(n);
    	}
    	BigInteger currentValue = mrMapFibonacci(n - 1).add(mrMapFibonacci(n - 2));
    	this.mrHashMap.put(n, currentValue);
    	return currentValue;
    }

    /**
	 * Dynamic Programming - Iterative Calculation with Memoization (Bottom to Top)
	 * 
     * Returns the value of Fibonacci number.
     * 
     * @param the number of Fibonacci number need to compute.
     *
     * @return the value of Fibonacci number.
     */
    BigInteger imFibonacci(int n) {
    	for (int i = 2; i <= n; i++) {
    		this.imArrayList.add(this.imArrayList.get(i-1).add(this.imArrayList.get(i-2)));
    	}
    	return this.imArrayList.get(n);
    }

    /**
	 * Iterative Calculation
	 * 
     * Returns the value of Fibonacci number.
     * 
     * @param the number of Fibonacci number need to compute.
     *
     * @return the value of Fibonacci number.
     */
    BigInteger iFibonacci(int n) {
    	BigInteger prev = new BigInteger("0");
    	BigInteger next = new BigInteger("1");
    	BigInteger f = new BigInteger("0");
    	if (n < 2) 
    		return new BigInteger(Integer.toString(n));
    	else {
    		for (int i = 2; i <= n; i++) {
    			f = prev.add(next);
    			prev = next;
    			next = f;
				//Debug Line
				//System.out.println("f(" + i + ")= " + f);
    		}
    		return f;
    	}

    }

    /**
	 * Lucas' Formula Method
	 *	Lucas' formula for the nth Fibonacci number F(n) is given by
	 *
	 *         ((1+sqrt(5))/2)^n - ((1-sqrt(5))/2)^n
	 *  F(n) = ------------------------------------- .
	 *                         sqrt(5)
	 *
	 * 
     * Returns the value of Fibonacci number.
     * 
     * @param the number of Fibonacci number need to compute.
     *
     * @return the value of Fibonacci number.
     */
    BigInteger fFibonacci(int n) {
    	double srt5 = Math.sqrt(5);
    	double p = (1+srt5)/2;
    	double q = (1-srt5)/2;
    	BigDecimal bsrt5 = new BigDecimal(srt5);
    	BigDecimal bp = new BigDecimal(p);
    	BigDecimal bq = new BigDecimal(q);
    	BigDecimal pq;

    	pq = bp.pow(n).subtract(bq.pow(n));

    	BigDecimal bg[] = pq.divideAndRemainder(bsrt5);

        //Debug print quotient and remainder
        //System.out.println("Quotient is " + bg[0] );
        //System.out.println("Remainder is " + bg[1] );
    	return bg[0].toBigInteger();

    }

    /**
	 * Print Calculation Result
	 * 
     * print output on screen.
     * 
     * @param method name, the number of Fibonacci number need to compute, the value of Fibonacci numbe, compute time.
     *
     * @return void.
     */
    void printResult(String method, int n, BigInteger ans, long time) {
    	System.out.println();
    	System.out.println("Method: " + method);
    	System.out.println("f(" + n + ")= " + ans);
    	System.out.printf("Computation time: %5.8f seconds.%n", time / 1.0e9);
    	System.out.println();
    }

    /**
	 * Input Menu
	 * 
     * print menu on screen and get the user input of the number of Fibonacci number need to compute.
     * 
     * @param null.
     *
     * @return the number of Fibonacci number need to compute.
     */
    int printMenu() {
    	int n = 0;
    	Scanner consoleInput = new Scanner(System.in);
    	System.out.println();
    	System.out.println("This program support the followings method to compute Fibonacci.");
    	System.out.println();
    	System.out.println("\t - Iterative Calculation");
    	System.out.println("\t - Recursive [Only for Fibonacci number <=37]");
    	System.out.println("\t - Lucas' Formula [Only for Fibonacci number <=500]");
    	System.out.println("\t - Dynamic Programming - [Only for Fibonacci number <=150,000]");
    	System.out.println("\t    - Iterative Calculation with Memoization (Bottom to Top)");
    	System.out.println("\t    - Recursive with Memoization Array (Top to Bottom)");
    	System.out.println("\t    - Recursive with Memoization HashMap (Top to Bottom)");
    	System.out.println();
    	System.out.println("Please enter an int number for Fibonacci?");
    	n = consoleInput.nextInt();
    	System.out.println("You have entered: " + n);
    	System.out.println("=========================================");

    	return n;
    }

    /**
     * Main
	 */
    public static void main(String[] args) {
    	int n = 0;
    	int loopCount = 0;
    	int stackSize = 3000;
    	BigInteger answer = new BigInteger("0");
    	long start = 0;
    	long time = 0;

		//init
    	FibonacciCalc q1 = new FibonacciCalc();

		//Menu
    	n = q1.printMenu();
    	loopCount = n / stackSize;

		//Iterative Calculation
    	start = System.nanoTime();
    	answer = q1.iFibonacci(n);
    	time = System.nanoTime() - start;
    	q1.printResult("Iterative Calculation", n, answer, time);

		//Recursive
    	if (n<=37) {
    		start = System.nanoTime();
    		answer = q1.rFibonacci(n);
    		time = System.nanoTime() - start;
    		q1.printResult("Recursive", n, answer, time);
    	}

		//Lucas' Formula
    	if (n<=500) {
    		start = System.nanoTime();
    		answer = q1.fFibonacci(n);
    		time = System.nanoTime() - start;
    		q1.printResult("Lucas' Formula", n, answer, time);
    	}

    	if (n<=150000){
        	//Dynamic Programming - Iterative Calculation with Memoization (Bottom to Top)
    		start = System.nanoTime();
    		answer = q1.imFibonacci(n);
    		time = System.nanoTime() - start;
    		q1.printResult("Dynamic Programming - Iterative Calculation with Memoization (Bottom to Top)", n, answer, time);


        	//Dynamic Programming - Recursive with Memoization Array (Top to Bottom)
    		start = System.nanoTime();
    		for (int i=1; i <=loopCount; i++){
    			answer = q1.mrFibonacci(i*stackSize);
    		}
    		answer = q1.mrFibonacci(n);
    		time = System.nanoTime() - start;
    		q1.printResult("Dynamic Programming - Recursive with Memoization Array (Top to Bottom)", n, answer, time);

			//Dynamic Programming - Recursive with Memoization HashMap (Top to Bottom)
    		start = System.nanoTime();
    		for (int i=1; i <=loopCount; i++){
    			answer = q1.mrMapFibonacci(i*stackSize);
    		}
    		answer = q1.mrMapFibonacci(n);
    		time = System.nanoTime() - start;
    		q1.printResult("Dynamic Programming - Recursive with Memoization HashMap (Top to Bottom)", n, answer, time);
    	}

    }



}