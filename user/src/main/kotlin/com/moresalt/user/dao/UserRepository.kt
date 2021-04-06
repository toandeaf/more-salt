package com.moresalt.user.dao

import com.moresalt.user.model.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository: PanacheRepository<User>