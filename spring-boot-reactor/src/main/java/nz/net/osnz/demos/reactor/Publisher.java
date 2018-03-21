package nz.net.osnz.demos.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kdeng on 7/03/14.
 */
@Service
class Publisher {

	private static final Logger LOG = LoggerFactory.getLogger(Publisher.class);

	@Autowired
	EventBus eventBus;

	@Autowired
	CountDownLatch latch;

	public void publishJokes(int numberOfJokes) throws InterruptedException {

		long start = System.currentTimeMillis();

		AtomicInteger counter = new AtomicInteger(1);

		for (int i = 0; i < numberOfJokes; i++) {
			eventBus.notify("jokes", Event.wrap(counter.getAndIncrement()));
		}

		latch.await();

		long elapsed = System.currentTimeMillis() - start;

		LOG.info("Elapsed time: " + elapsed + "ms");
		LOG.info("Average time per joke: " + elapsed / numberOfJokes + "ms");
	}

}
