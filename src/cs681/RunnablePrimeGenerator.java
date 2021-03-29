package cs681;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {


		RunnablePrimeGenerator gen = new RunnablePrimeGenerator(1L, 2000000L);
		
		Thread t = new Thread(gen);
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {}

		Instant start = Instant.now();    

		gen.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );

		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();


		long primeNum = gen.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
		System.out.println("Elapsed time using one thread: " + timeElapsed + " miliseconds.");
        
	}

}
