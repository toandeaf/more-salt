package com.moresalt.user.model

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.RequestScoped
import javax.persistence.*
import com.moresalt.grpc.user.User as GrpcUser

@Entity
@Table(name = "usr")
data class User
    (
    @Id @GeneratedValue var id: Long? = null,
    var username: String = "",
    var email: String = "",
    var password: String = "",
    @OneToOne(cascade = [CascadeType.ALL]) var tasteProfile: TasteProfile = TasteProfile(),
    @OneToOne(cascade = [CascadeType.ALL]) var ambitions: Ambition = Ambition(),
) : PanacheEntityBase() {
    // Construct from gRPC instance
    constructor(user: GrpcUser) : this(
        id = if (user.id != 0L) user.id else null,
        username = user.username,
        email = user.email,
        password = user.password,
        tasteProfile = TasteProfile(user.tasteProfile),
        ambitions = Ambition(user.ambition)
    )
    fun convertToGrpc(): GrpcUser {
        val newUser = GrpcUser.newBuilder()
            .setEmail(this.email)
            .setPassword(this.password)
            .setUsername(this.username)
        this.id?.let { newUser.setId(it) }
        this.ambitions?.let { newUser.setAmbition(it.convertToGrpc()) }
        this.tasteProfile?.let { newUser.setTasteProfile(it.convertToGrpc()) }
        return newUser.build()
    }
}