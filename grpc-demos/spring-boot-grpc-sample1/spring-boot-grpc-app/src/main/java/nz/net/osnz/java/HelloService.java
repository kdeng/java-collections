package nz.net.osnz.java;

import io.grpc.stub.StreamObserver;
import io.osnz.demos.grpc.helloworld.Greeting;
import io.osnz.demos.grpc.helloworld.HelloWorldServiceGrpc;
import io.osnz.demos.grpc.helloworld.Person;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@GRpcService
public class HelloService extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

  @Override
  public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
    LOGGER.info("server received {}", request);

    String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
    Greeting greeting = Greeting.newBuilder().setMessage(message).build();
    LOGGER.info("server responded {}", greeting);

    responseObserver.onNext(greeting);
    responseObserver.onCompleted();
  }

}
