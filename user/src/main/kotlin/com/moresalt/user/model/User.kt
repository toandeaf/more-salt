package com.moresalt.user.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.Table
import com.moresalt.grpc.user.User as GrpcUser

@Entity
@Table(name = "usr")
open class User
    (
    open var username: String = "",
    open var email: String = "",
    open var password: String = "",
    @OneToOne(cascade = [CascadeType.ALL]) open var tasteProfile: TasteProfile? = null,
    @OneToOne(cascade = [CascadeType.ALL]) open var ambitions: com.moresalt.user.model.Ambition? = null
) : PanacheEntity() {
    companion object {
        fun parseFromGrpc(user: GrpcUser): User {
            return User(
                username = user.username,
                email = user.email,
                password = user.password,
                tasteProfile = TasteProfile.parseFromGrpc(),
                ambitions = Ambition.parseFromGrpc()
            )
        }
        fun convertToGrpc(user: User): GrpcUser {
            val newUser = GrpcUser.newBuilder()
                .setEmail(user.email)
                .setPassword(user.password)
                .setUsername(user.username)
            user.id?.let { newUser.setId(it) }
            return newUser.build()
        }
    }
}