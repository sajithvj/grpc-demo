package com.example.grpcdemo;

import com.example.grpcdemo.proto.HelloReply;
import com.example.grpcdemo.proto.HelloRequest;
import com.example.grpcdemo.proto.HelloWorldGrpc;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.grpc.test.autoconfigure.AutoConfigureTestGrpcTransport;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.grpc.client.ImportGrpcClients;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestGrpcTransport
@ImportGrpcClients(types = HelloWorldGrpc.HelloWorldBlockingStub.class)
class GrpcDemoApplicationTests {

    @Autowired
    private HelloWorldGrpc.HelloWorldBlockingStub helloStub;

    @Test
    void sayHello() {
        HelloRequest request = HelloRequest.newBuilder().setName("Spring").build();
        HelloReply reply = this.helloStub.sayHello(request);
        assertThat(reply.getMessage()).isEqualTo("Hello 'Spring'");
    }

}
