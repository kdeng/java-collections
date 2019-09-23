package io.osnz.demos.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.osnz.demos.grpc.HelloRequest;
import io.osnz.demos.grpc.HelloResponse;
import io.osnz.demos.grpc.HelloServiceGrpc;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class GrpcBlockingClient {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
      .usePlaintext()
      .build();

    HelloServiceGrpc.HelloServiceBlockingStub stub
      = HelloServiceGrpc.newBlockingStub(channel);

    HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
      .setFirstName("Baeldung")
      .setLastName("gRPC")
      .build());

    System.out.println("Response : " + helloResponse.getGreeting());

    channel.shutdown();
  }

}
