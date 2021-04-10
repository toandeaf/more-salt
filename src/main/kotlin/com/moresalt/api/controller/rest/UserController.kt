package com.moresalt.api.controller.rest

import com.moresalt.grpc.user.MutinyUserServiceGrpc
import com.moresalt.grpc.user.Process
import com.moresalt.grpc.user.Type
import com.moresalt.grpc.user.UserRequest
import com.moresalt.user.model.User
import io.quarkus.grpc.runtime.annotations.GrpcService
import io.smallrye.mutiny.Uni
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
class UserController {

    @Inject
    @GrpcService("user")
    lateinit var userClient: MutinyUserServiceGrpc.MutinyUserServiceStub

    @GET
    @Path("/{userId}")
    fun fetchUser(@PathParam("userId") userId: Long): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.FETCH, User(id = userId)))
            .onItem()
            .transform { User.parseFromGrpc(it.user) }
    }

    @POST
    fun createUser(user: User): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.CREATE, user))
            .onItem()
            .transform { User.parseFromGrpc(it.user) }
    }

    @DELETE
    fun deleteUser(user: User): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.DELETE, user))
            .onItem()
            .transform { User.parseFromGrpc(it.user) }
    }

    @PUT
    fun updateUser(user: User): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.UPDATE, user))
            .onItem()
            .transform { User.parseFromGrpc(it.user) }
    }

    private fun userRequest(process: Process, user: User): UserRequest {
        return UserRequest.newBuilder()
            .setUser(User.convertToGrpc(user))
            .setProcess(process)
            .setType(Type.USER)
            .build()
    }
}