package com.moresalt.user.service

import com.moresalt.grpc.user.Status
import com.moresalt.grpc.user.UserRequest
import com.moresalt.grpc.user.UserResponse
import com.moresalt.user.dao.UserRepository
import com.moresalt.user.model.User
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
open class UserService {

    @Inject
    open lateinit var repo: UserRepository

    private fun createUser(user: User) {
       repo.persist(user)
    }

    private fun fetchUser(userId: Long): User? {
        return repo.findById(userId)
    }

    private fun updateUser(user: User) {
        repo.persist(user)
    }

    private fun deleteUser(user: User) {
        return repo.delete(user)
    }

    fun processRequest(
        process: UserRequest.Process,
        user: com.moresalt.grpc.user.User) {
        when (process) {
            UserRequest.Process.CREATE -> {
                createUser(User.parseFromGrpc(user))
            }
            UserRequest.Process.FETCH -> {
                fetchUser(user.id)
            }
            UserRequest.Process.UPDATE -> {
                updateUser(User.parseFromGrpc(user))
            }
            UserRequest.Process.DELETE -> {
                deleteUser(User.parseFromGrpc(user))
            }
            else -> {
                println("Operation not supported!")
            }
        }
    }

    private fun convertToResponse(fetched: User?): UserResponse {
        if(fetched != null) {
            return UserResponse.newBuilder()
                .setUser(User.convertToGrpc(fetched))
                .setStatus(Status.newBuilder()
                    .setMessage("Worked.")
                    .setCode(200)
                    .setSuccess(true))
                .build()
        } else {
            return UserResponse.newBuilder()
                .setStatus(Status.newBuilder()
                    .setMessage("Didn't work.")
                    .setCode(404)
                    .setSuccess(false))
                .build()
        }
    }
}