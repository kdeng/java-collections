package nz.net.osnz;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * This test aims to approve both {@link Future#get()} and {@link FutureTask#get()} are block method.
 *
 * @author Kefeng Deng (deng@51any.com)
 */
public class CallableTest {

	private class CallableDemo implements Callable<Integer> {

		private int sum;

		@Override
		public Integer call() throws Exception {
			System.out.println("Callable => start child thread");
			Thread.sleep(3000);

			for (int i = 0; i < 5000; i++) {
				sum = sum + i;
			}

			System.out.println("Callable => finish child thread");
			return sum;
		}

	}


	@Test
	public void testCallableWithFuture() throws Exception {
		ExecutorService es = Executors.newSingleThreadExecutor();
		CallableDemo calTask=new CallableDemo();
		Future<Integer> future =es.submit(calTask);
		es.shutdown();
		System.out.println("start main thread job");
		Assert.assertNotNull(future.get());
		System.out.println("finish main thread job");
	}

	@Test
	public void testCallableWithFutureTask() throws Exception {
		ExecutorService es = Executors.newSingleThreadExecutor();
		CallableDemo calTask=new CallableDemo();
		FutureTask<Integer> futureTask=new FutureTask<>(calTask);
		es.submit(futureTask);
		es.shutdown();
		Thread.sleep(2000);
		System.out.println("start main thread job");
		Assert.assertNotNull(futureTask.get());
		System.out.println("finish main thread job");
	}

}
