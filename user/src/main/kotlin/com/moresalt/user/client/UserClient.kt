package com.moresalt.user.client

import com.moresalt.grpc.user.UserServiceGrpc
import io.grpc.ManagedChannelBuilder

class UserClient {
    private val channel = ManagedChannelBuilder.forAddress("localhost", 9001).usePlaintext().build()
    val blockingStub: UserServiceGrpc.UserServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel)
    val futureStub: UserServiceGrpc.UserServiceFutureStub = UserServiceGrpc.newFutureStub(channel)
}