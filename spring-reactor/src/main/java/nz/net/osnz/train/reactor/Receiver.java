package nz.net.osnz.train.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.event.Event;
import reactor.function.Consumer;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kdeng on 7/03/14.
 */
@Service
class Receiver implements Consumer<Event<Integer>> {

    @Autowired CountDownLatch latch;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void accept(Event<Integer> integerEvent) {

        JokeResource jokeResource = restTemplate.getForObject("http://api.icndb.com/jokes/random", JokeResource.class);

        System.out.println("Joke " + integerEvent.getData() + ": " + jokeResource.getValue().getJoke());

        latch.countDown();

    }
}
