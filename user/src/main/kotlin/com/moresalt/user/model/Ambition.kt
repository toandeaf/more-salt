package com.moresalt.user.model

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table
import com.moresalt.grpc.user.Ambition as GrpcAmbition

@Entity
@Table(name = "ambition")
data class Ambition
    (
    var instructions: String = "",
    var variety: String = "",
    var complexity: String = "",
    var social: String = "",
) : PanacheEntity() {
    // Construct from gRPC instance
    constructor(ambition: GrpcAmbition) : this(
        instructions = ambition.instructions,
        variety = ambition.variety,
        complexity = ambition.complexity,
        social = ambition.social
    )
    fun convertToGrpc(): GrpcAmbition {
        return GrpcAmbition.newBuilder()
            .setInstructions(this.instructions)
            .setVariety(this.variety)
            .setComplexity(this.complexity)
            .setSocial(this.social)
            .build()
    }
}


