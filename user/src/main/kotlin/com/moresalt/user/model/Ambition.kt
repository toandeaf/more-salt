package com.moresalt.user.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table
import com.moresalt.grpc.user.Ambition as GrpcAmbition

@Entity
@Table(name = "ambition")
open class Ambition
    (
    open var instructions: String = "",
    open var variety: String = "",
    open var complexity: String = "",
    open var social: String = ""
    ) : PanacheEntity() {
    companion object {
        fun parseFromGrpc(ambition: GrpcAmbition): Ambition {
            println(ambition)
            return Ambition(
                instructions = ambition.instructions,
                variety = ambition.variety,
                complexity = ambition.complexity,
                social = ambition.social
            )
        }

        fun convertToGrpc(ambition: Ambition): GrpcAmbition {
            println(ambition)
            return GrpcAmbition.newBuilder()
                .setInstructions(ambition.instructions)
                .setVariety(ambition.variety)
                .setComplexity(ambition.complexity)
                .setSocial(ambition.social)
                .build()
        }
    }
}


