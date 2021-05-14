package com.moresalt.user.dao

import com.moresalt.user.model.User
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
@Transactional
class UserRepository: PanacheRepository<User>