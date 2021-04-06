package com.moresalt.user.controller

import com.moresalt.grpc.user.MutinyUserServiceGrpc
import com.moresalt.grpc.user.UserRequest
import com.moresalt.grpc.user.UserResponse
import com.moresalt.user.service.UserService
import io.smallrye.common.annotation.Blocking
import io.smallrye.mutiny.Uni
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServer: MutinyUserServiceGrpc.UserServiceImplBase() {

    @Inject
    lateinit var userService: UserService

    @Blocking
    override fun processUserRequest(request: UserRequest?): Uni<UserResponse> {
        return userService.processRequest(request!!.process, request.user)
    }
}