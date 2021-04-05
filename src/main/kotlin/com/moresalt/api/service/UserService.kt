package com.moresalt.api.service

import com.moresalt.api.dao.UserRepository
import com.moresalt.api.model.User
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
open class UserService {

    @Inject
    open lateinit var repo: UserRepository

    fun deleteUser(user: User) {
        repo.delete(user)
    }

    fun createUser(user: User) {
        repo.persist(user)
    }

    fun fetchUser(userId: Long): User? {
        return repo.findById(userId)
    }

    fun updateUser(user: User) {
        repo.persist(user)
    }
}