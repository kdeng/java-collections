package io.osnz.akka;

import akka.actor.UntypedAbstractActor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Slf4j
public class ActorWorker extends UntypedAbstractActor {

  public static enum Msg {
    GREET, DONE;
  }

  @Override
  public void preStart() {
    log.info("preStart");
  }

  @Override
  public void postStop() {
    log.info("postStop");
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    log.info("OnReceive: {}", message);
    if (message == Msg.GREET) {
      System.out.println("Hello World!");
      Thread.sleep(1000);
      getSender().tell(Msg.DONE, getSelf());
    } else
      unhandled(message);
  }


}
