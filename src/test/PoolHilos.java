package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolHilos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		//anadimos 100 hilos al executor, que se iran ejecutando de a 2 
		
		for (int i = 0; i < 100; i++) {
			executor.execute(new MiHilo(i));
		}
		
		executor.shutdown();

	}

}
