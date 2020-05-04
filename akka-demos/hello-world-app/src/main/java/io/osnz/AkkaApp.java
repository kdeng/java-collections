package io.osnz;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.osnz.akka.HelloWorld;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Slf4j
public class AkkaApp {

  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("hello");
    ActorRef ref = system.actorOf(Props.create(HelloWorld.class), "hello");
    log.info("ActorRef : {}", ref.path());
  }

}
