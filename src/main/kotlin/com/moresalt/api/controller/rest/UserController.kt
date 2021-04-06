package com.moresalt.api.controller.rest

import com.moresalt.grpc.user.UserRequest
import com.moresalt.grpc.user.UserServiceGrpc
import com.moresalt.user.client.UserClient
import com.moresalt.user.model.User
import javax.annotation.PostConstruct
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import com.moresalt.grpc.user.User as GrpcUser

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
class UserController {
    private val userClient: UserClient = UserClient()
    private lateinit var userStub: UserServiceGrpc.UserServiceBlockingStub

    @PostConstruct
    private fun init() {
        userStub = userClient.blockingStub
    }

    @GET
    @Path("/{userId}")
    fun fetchUser(@PathParam("userId") userId: Long): Any {
        val response = userStub.processUserRequest(
            UserRequest.newBuilder()
                .setUser(GrpcUser.newBuilder()
                        .setId(userId)
                        .build()
                )
                .setProcess(UserRequest.Process.FETCH)
                .build()
        )
        return User.parseFromGrpc(response.user)
    }

    @POST
    fun createUser(user: User): User {
        val response = userStub.processUserRequest(
            UserRequest.newBuilder()
                .setUser(User.convertToGrpc(user))
                .setProcess(UserRequest.Process.CREATE)
                .build()
        )
        return User.parseFromGrpc(response.user)
    }

    @DELETE
    fun deleteUser(user: User): User {
        val response = userStub.processUserRequest(
            UserRequest.newBuilder()
                .setUser(User.convertToGrpc(user))
                .setProcess(UserRequest.Process.DELETE)
                .build()
        )
        return User.parseFromGrpc(response.user)
    }

    @PUT
    fun updateUser(user: User): User {
        val response = userStub.processUserRequest(
            UserRequest.newBuilder()
                .setUser(User.convertToGrpc(user))
                .setProcess(UserRequest.Process.UPDATE)
                .build()
        )
        return User.parseFromGrpc(response.user)
    }
}