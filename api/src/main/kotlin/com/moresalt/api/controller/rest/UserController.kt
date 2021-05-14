package com.moresalt.api.controller.rest

import com.moresalt.grpc.user.MutinyUserServiceGrpc
import com.moresalt.grpc.user.Process
import com.moresalt.grpc.user.Type
import com.moresalt.grpc.user.UserRequest
import com.moresalt.user.model.Ambition
import com.moresalt.user.model.TasteProfile
import com.moresalt.user.model.User
import io.quarkus.grpc.runtime.annotations.GrpcService
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
class UserController {

    @Inject
    @GrpcService("user")
    lateinit var userClient: MutinyUserServiceGrpc.MutinyUserServiceStub

    @GET
    @Path("/{userId}")
    fun fetchUser(@PathParam("userId") userId: Long): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.FETCH, User(id = userId)))
            .onItem()
            .transform { User(it.user) }
    }

    @POST
    fun createUser(user: User): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.CREATE, user))
            .onItem()
            .transform { User(it.user) }
    }

    @DELETE
    fun deleteUser(user: User): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.DELETE, user))
            .onItem()
            .transform { User(it.user) }
    }

    @PUT
    fun updateUser(user: User): Uni<User> {
        return userClient.processUserRequest(userRequest(Process.UPDATE, user))
            .onItem()
            .transform { User(it.user) }
    }

    @GET
    @Path("/test")
    fun createTestUser(): Uni<User> {
        println("trying")

        val testUser = User(
            username = "testname",
            email = "email.com",
            password = "newPass",
            tasteProfile = TasteProfile(),
            ambitions = Ambition(
                instructions = "complex",
                variety = "various",
                social = "social",
                complexity = "complex"
            )
        )
        return userClient.processUserRequest(userRequest(Process.CREATE, testUser))
            .onItem()
            .transform { User(it.user) }
    }

    private fun userRequest(process: Process, user: User): UserRequest {
        return UserRequest.newBuilder()
            .setUser(user.convertToGrpc())
            .setProcess(process)
            .setType(Type.USER)
            .build()
    }
}