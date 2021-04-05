package com.moresalt.api.controller.grpc


import com.moresalt.grpc.UserSearchRequest
import com.moresalt.grpc.UserSearchResponse
import com.moresalt.grpc.UserServiceGrpc
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class UserService: UserServiceGrpc.UserServiceImplBase() {
    override fun userSearch(request: UserSearchRequest?, responseObserver: StreamObserver<UserSearchResponse>?) {
        super.userSearch(request, responseObserver)
    }
}