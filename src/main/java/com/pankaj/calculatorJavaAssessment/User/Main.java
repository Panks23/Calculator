package com.pankaj.calculatorJavaAssessment.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import com.pankaj.calculatorJavaAssessment.Calculator;

public class Main {
	
	public static void main(String [] args) {
	List<User> userList = Arrays.asList(new User("Name1", Calculator.builder().build()),
			new User("Name1", Calculator.builder().build()),
			new User("Name2", Calculator.builder().build()),
			new User("Name3", Calculator.builder().build()),
			new User("Name4", Calculator.builder().build()),
			new User("Name5", Calculator.builder().build()),
			new User("Name6", Calculator.builder().build()),
			new User("Name7", Calculator.builder().build()),
			new User("Name8", Calculator.builder().build())
			);
	
	ExecutorService service = Executors.newFixedThreadPool(10);
	
	
	List<Future<Integer>> listOfFuture = new ArrayList<>()	;
	for(int i=0; i<userList.size(); i++) {
		
		AtomicInteger index = new AtomicInteger();
		index.set(i);
		Future<Integer> future =  service.submit(()->{
			Calculator calc = userList.get(index.get()).getCalculator();
			List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			int num = calc.add(numbers);
			return num;
		});
		listOfFuture.add(future);
	}
	List<Integer> resultList = new ArrayList<>();
	for(int i=0; i<listOfFuture.size(); i++) {
		try {
			resultList.add(listOfFuture.get(i).get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	Double avg = (double) (resultList.stream().map(i -> i*2).reduce(0, (res, num) -> res+num)/resultList.size());
	
	System.out.println(avg);
	}

}
