package com.example.grpcdemo;

import com.example.grpcdemo.proto.HelloReply;
import com.example.grpcdemo.proto.HelloRequest;
import com.example.grpcdemo.proto.HelloWorldGrpc;

import com.example.grpcdemo.service.KafkaProducerService;
import io.grpc.stub.StreamObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

// Any bean implementing BindableService is auto-exposed as a gRPC endpoint.
// All classes generated from a .proto file implement BindableService already,
// so @GrpcService (making this a Spring bean) is all that's required.
@GrpcService
public class MyHelloWorldService extends HelloWorldGrpc.HelloWorldImplBase {


    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello '%s'".formatted(request.getName()))
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }

}
