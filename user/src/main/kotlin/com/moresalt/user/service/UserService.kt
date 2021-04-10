package com.moresalt.user.service

import com.moresalt.grpc.user.Process
import com.moresalt.grpc.user.Status
import com.moresalt.grpc.user.UserResponse
import com.moresalt.user.dao.UserRepository
import com.moresalt.user.model.User
import io.smallrye.common.annotation.Blocking
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.groups.UniSubscribe
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
        println("Trying to create")

        repo.persist(user)
        return user
    }

    private fun fetchUser(user: User): User? {
        println("Trying to fetch")
        println(user)
        val userrr = repo.findById(user.id!!)
        println(userrr)
        return userrr
    }

    private fun updateUser(user: User): User {
        println("Trying to update")
        repo.persist(user)
        return user
    }

    private fun deleteUser(user: User): User {
        println("Trying to delete")
        repo.delete(user)
        return user
    }

    fun processRequest(process: Process, user: GrpcUser): Uni<UserResponse> {
        val function = when (process) {
            Process.CREATE -> ::createUser
            Process.FETCH -> ::fetchUser
            Process.UPDATE -> ::updateUser
            Process.DELETE -> ::deleteUser
            else -> ::returnError
        }

        println("Returning")

        return Uni.createFrom()
            .item(function(User.parseFromGrpc(user)))
            .onItem()
            .transform { fetched -> convertToResponse(fetched) }
    }

    private fun returnError(user: User): User? {
        println(user)
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