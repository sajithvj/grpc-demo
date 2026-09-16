package com.example.grpcdemo;

import com.example.grpcdemo.proto.HelloReply;
import com.example.grpcdemo.proto.HelloRequest;
import com.example.grpcdemo.proto.HelloWorldGrpc.HelloWorldBlockingStub;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Demonstrates using the injected gRPC client stub like any other Spring bean.
// This runs automatically on application startup and calls the server
// defined in MyHelloWorldService over the network (localhost:9090).
@Component
class HelloClientRunner implements ApplicationRunner {

    private final HelloWorldBlockingStub helloStub;

    HelloClientRunner(HelloWorldBlockingStub helloStub) {
        this.helloStub = helloStub;
    }

    @Override
    public void run(ApplicationArguments args) {
        HelloRequest request = HelloRequest.newBuilder().setName("Spring").build();
        HelloReply reply = this.helloStub.sayHello(request);
        System.out.println(">>> gRPC client received: " + reply.getMessage());
    }

}
