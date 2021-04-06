package com.moresalt.user.controller



import com.moresalt.grpc.user.User
import com.moresalt.grpc.user.UserRequest
import com.moresalt.grpc.user.UserResponse
import com.moresalt.grpc.user.UserServiceGrpc
import com.moresalt.user.service.UserService
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServer: UserServiceGrpc.UserServiceImplBase() {

    @Inject
    lateinit var userService: UserService
    override fun processUserRequest(request: UserRequest?, responseObserver: StreamObserver<UserResponse>?) {
        userService.processRequest(request!!.process, request.user)
        responseObserver?.onCompleted()
    }
}