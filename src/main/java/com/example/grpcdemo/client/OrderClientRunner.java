package com.example.grpcdemo.client;

import com.example.grpcdemo.proto.GetOrderServiceGrpc.GetOrderServiceBlockingStub;
import com.example.grpcdemo.proto.OrderRequest;
import com.example.grpcdemo.proto.OrderResponse;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderClientRunner implements ApplicationRunner {

  private final GetOrderServiceBlockingStub orderStub;

  public OrderClientRunner(GetOrderServiceBlockingStub orderStub) {
        this.orderStub = orderStub;
    }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    OrderRequest request = OrderRequest.newBuilder().setId("123").build();
    // Call the gRPC service using the orderStub
    // orderStub.getOrder(request);
    OrderResponse orderResponse = orderStub.getOrder(request);
    System.out.println(">>> gRPC client received order: " + orderResponse.getMessage());
  }

  // You can add methods here to call the gRPC service using the orderStub

}
