package com.moresalt.api.dao

import com.moresalt.api.model.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
 class UserRepository: PanacheRepository<User> {
}