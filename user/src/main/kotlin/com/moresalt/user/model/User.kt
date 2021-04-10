package com.moresalt.user.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.*
import com.moresalt.grpc.user.User as GrpcUser

@Entity
@Table(name = "usr")
open class User
    (
    @Id @GeneratedValue open var id: Long? = null,
    open var username: String = "",
    open var email: String = "",
    open var password: String = "",
    @OneToOne(cascade = [CascadeType.ALL]) open var tasteProfile: TasteProfile? = null,
    @OneToOne(cascade = [CascadeType.ALL]) open var ambitions: Ambition? = null
    ) : PanacheEntityBase {
    companion object {
        fun parseFromGrpc(user: GrpcUser): User {
            return User(
                id = if(user.id == 0L) null else user.id,
                username = user.username,
                email = user.email,
                password = user.password,
                tasteProfile = TasteProfile.parseFromGrpc(user.tasteProfile),
                ambitions = Ambition.parseFromGrpc(user.ambition)
            )
        }

        fun convertToGrpc(user: User): GrpcUser {
            val newUser = GrpcUser.newBuilder()
                .setEmail(user.email)
                .setPassword(user.password)
                .setUsername(user.username)
            user.id?.let { newUser.setId(it) }
            user.ambitions?.let { newUser.setAmbition(Ambition.convertToGrpc(it)) }
            user.tasteProfile?.let { newUser.setTasteProfile(TasteProfile.convertToGrpc(it)) }
            return newUser.build()
        }
    }
}