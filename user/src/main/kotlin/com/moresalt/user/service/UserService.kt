package com.moresalt.user.service

import com.moresalt.grpc.user.Status
import com.moresalt.grpc.user.UserRequest
import com.moresalt.grpc.user.UserResponse
import com.moresalt.user.dao.UserRepository
import com.moresalt.user.model.User
import io.smallrye.mutiny.Uni
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

import com.moresalt.grpc.user.User as GrpcUser

@Singleton
@Transactional
open class UserService {

    @Inject
    open lateinit var repo: UserRepository

    private fun createUser(user: User): User {
        repo.persist(user)
        return user
    }

    private fun fetchUser(user: User): User? {
        return repo.findById(user.id!!)
    }

    private fun updateUser(user: User): User {
        repo.persist(user)
        return user
    }

    private fun deleteUser(user: User): User {
        repo.delete(user)
        return user
    }

    fun processRequest(process: UserRequest.Process, user: GrpcUser): Uni<UserResponse> {
        val function = when (process) {
            UserRequest.Process.CREATE -> ::createUser
            UserRequest.Process.FETCH -> ::fetchUser
            UserRequest.Process.UPDATE -> ::updateUser
            UserRequest.Process.DELETE -> ::deleteUser
            else -> ::returnError
        }

        return Uni.createFrom()
            .item(function(User.parseFromGrpc(user)))
            .onItem()
            .transform { fetched -> convertToResponse(fetched) }
    }

    private fun returnError(user: User): User? {
        return null
    }

    private fun convertToResponse(fetched: User?): UserResponse {
        if (fetched != null) {
            return UserResponse.newBuilder()
                .setUser(User.convertToGrpc(fetched))
                .setStatus(
                    Status.newBuilder()
                        .setMessage("Worked.")
                        .setCode(200)
                        .setSuccess(true)
                )
                .build()
        } else {
            return UserResponse.newBuilder()
                .setStatus(
                    Status.newBuilder()
                        .setMessage("Didn't work.")
                        .setCode(404)
                        .setSuccess(false)
                )
                .build()
        }
    }
}