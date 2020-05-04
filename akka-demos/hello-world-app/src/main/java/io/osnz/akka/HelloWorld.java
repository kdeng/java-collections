package io.osnz.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Slf4j
public class HelloWorld extends UntypedAbstractActor {

  @Override
  public void preStart() {
    log.info("preStart");
    // create the greeter actor
    final ActorRef greeter = getContext().actorOf(Props.create(ActorWorker.class), "greeter");
    // tell it to perform the greeting
    greeter.tell(ActorWorker.Msg.GREET, getSelf());
  }

  @Override
  public void postStop() {
    log.info("postStop");
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    log.info("onReceive: {}", message);
    if (message == ActorWorker.Msg.DONE) {
      // when the greeter is done, stop this actor and with it the application
      getContext().stop(getSelf());
    } else
      unhandled(message);
  }

}
