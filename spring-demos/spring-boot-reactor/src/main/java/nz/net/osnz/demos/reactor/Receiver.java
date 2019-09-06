package nz.net.osnz.demos.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kdeng on 7/03/14.
 */
@Service
class Receiver implements Consumer<Event<Integer>> {

  private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

  @Autowired
  CountDownLatch latch;

  RestTemplate restTemplate = new RestTemplate();

  public void accept(Event<Integer> integerEvent) {

    JokeResource jokeResource = restTemplate.getForObject("http://api.icndb.com/jokes/random", JokeResource.class);

    LOG.info("Joke " + integerEvent.getData() + ": " + jokeResource.getValue().getJoke());

    latch.countDown();

  }
}
