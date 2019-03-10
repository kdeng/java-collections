package io.osnz.demos.grpc.server;

import io.grpc.stub.StreamObserver;
import io.osnz.demos.grpc.HelloRequest;
import io.osnz.demos.grpc.HelloResponse;
import io.osnz.demos.grpc.HelloServiceGrpc;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

  @Override
  public void hello(
      HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

    String greeting = new StringBuilder()
        .append("Hello, ")
        .append(request.getFirstName())
        .append(" ")
        .append(request.getLastName())
        .toString();

    HelloResponse response = HelloResponse.newBuilder()
        .setGreeting(greeting)
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

}
