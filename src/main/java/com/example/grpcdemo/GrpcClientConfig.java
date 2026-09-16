package com.example.grpcdemo;

import com.example.grpcdemo.proto.GetOrderServiceGrpc;
import com.example.grpcdemo.proto.HelloWorldGrpc;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.ImportGrpcClients;
@Configuration
// "target = hello" is a logical channel name, resolved via
// spring.grpc.client.channel.hello.target in application.yml
@ImportGrpcClients(target = "hello", types = HelloWorldGrpc.HelloWorldBlockingStub.class)
@ImportGrpcClients(target = "getOrder", types = GetOrderServiceGrpc.GetOrderServiceBlockingStub.class)

public class GrpcClientConfig {

}
