package com.example.grpcdemo.service;

import com.example.grpcdemo.proto.OrderResponse;
import com.example.grpcdemo.proto.OrderRequest;
import com.example.grpcdemo.proto.GetOrderServiceGrpc;

import io.grpc.stub.StreamObserver;

import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class OrderGrpcService extends GetOrderServiceGrpc.GetOrderServiceImplBase{

  @Override
  public void getOrder(OrderRequest request,
      StreamObserver<OrderResponse> responseObserver) {
       OrderResponse orderResponse= OrderResponse.newBuilder().setMessage("Order fetched").build();
    responseObserver.onNext(orderResponse);
    responseObserver.onCompleted();
  }

}
