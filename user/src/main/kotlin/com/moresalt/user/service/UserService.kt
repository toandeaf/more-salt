package com.moresalt.user.service

import com.moresalt.grpc.user.Process
import com.moresalt.grpc.user.Status
import com.moresalt.grpc.user.UserResponse
import com.moresalt.user.dao.UserRepository
import com.moresalt.user.model.User
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import com.moresalt.grpc.user.User as GrpcUser

@ApplicationScoped
class UserService {

    @Inject
    lateinit var repo: UserRepository

    private fun createUser(user: User): Uni<UserResponse> {
        println("method")
        return repo.persistAndFlush(user)
            .onItem()
            .transform { convertToResponse(true, user) }
    }

    private fun fetchUser(user: User): Uni<UserResponse> {
        return repo.findById(1L)
            .onItem()
            .transform { item -> convertToResponse(true, item) }
    }

    private fun updateUser(user: User): Uni<UserResponse> {
        return repo.persist(user)
            .onItem()
            .transform { convertToResponse(true, user) }
    }

    private fun deleteUser(user: User): Uni<UserResponse> {
        return repo.delete(user)
            .onItem()
            .transform { convertToResponse(true, user) }
    }

    fun processRequest(process: Process, user: GrpcUser): Uni<UserResponse> {
        println("fetching")
        val function = when (process) {
            Process.CREATE -> ::createUser
            Process.FETCH -> ::fetchUser
            Process.UPDATE -> ::updateUser
            Process.DELETE -> ::deleteUser
            else -> ::returnError
        }
        return function(User(user))
    }

    private fun returnError(user: User): Uni<UserResponse> {
        return Uni.createFrom().item(convertToResponse(false, null))
    }

    private fun convertToResponse(result: Boolean, entity: Any?): UserResponse {
        println("Tries to convert response")
        val response = UserResponse.newBuilder()
        val status = Status.newBuilder()
        when(result) {
            true -> {
                status.code = 200
                status.message = "User request successful."
                status.success = true
                if(entity is User) {
                    response.user = entity.convertToGrpc()
                }
            }
            false -> {
                status.code = 500
                status.message = "Error in request."
                status.success = false
            }
        }
        response.status = status.build()
        return response.build()
    }

}