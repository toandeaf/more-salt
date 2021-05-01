package com.moresalt.user.model

import io.quarkus.hibernate.reactive.panache.PanacheEntity
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase
import javax.persistence.*
import com.moresalt.grpc.user.TasteProfile as GrpcTasteProfile

@Entity
@Table(name = "taste_profile")
data class TasteProfile
    (
    var sweetness: Int = 3,
    var bitterness: Int = 3,
) : PanacheEntity() {
    // Construct from gRPC instance
    constructor(tasteProfile: GrpcTasteProfile) : this(
        sweetness = tasteProfile.sweetness,
        bitterness = tasteProfile.bitterness
    )
    fun convertToGrpc(): GrpcTasteProfile {
        return GrpcTasteProfile.newBuilder()
            .setSweetness(this.sweetness)
            .setBitterness(this.bitterness)
            .build()
    }
}