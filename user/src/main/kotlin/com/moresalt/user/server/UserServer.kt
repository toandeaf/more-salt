package com.moresalt.user.server

import com.moresalt.grpc.user.MutinyUserServiceGrpc
import com.moresalt.grpc.user.User
import com.moresalt.grpc.user.UserRequest
import com.moresalt.grpc.user.UserResponse
import com.moresalt.user.service.UserService
import io.smallrye.mutiny.Uni
import javax.enterprise.context.spi.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServer: MutinyUserServiceGrpc.UserServiceImplBase() {

    @Inject
    lateinit var userService: UserService

    override fun processUserRequest(request: UserRequest?): Uni<UserResponse> {
        return userService.processRequest(request!!.process, request.user)
    }
}