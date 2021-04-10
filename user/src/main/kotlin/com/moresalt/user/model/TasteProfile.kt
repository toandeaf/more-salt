package com.moresalt.user.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

import com.moresalt.grpc.user.TasteProfile as GrpcTasteProfile

@Entity
@Table(name = "taste_profile")
open class TasteProfile
    (
    open var sweetness: Int = 3,
    open var bitterness: Int = 3
    ): PanacheEntity() {
        companion object {
            fun parseFromGrpc(tasteProfile: GrpcTasteProfile)
            : TasteProfile {
                println(tasteProfile)
                return TasteProfile(
                    sweetness = tasteProfile.sweetness,
                    bitterness = tasteProfile.bitterness
                )
            }

            fun convertToGrpc(tasteProfile: TasteProfile)
            : GrpcTasteProfile {
                println(tasteProfile)
                return GrpcTasteProfile.newBuilder()
                    .setSweetness(tasteProfile.sweetness)
                    .setBitterness(tasteProfile.bitterness)
                    .build()
            }
        }
    }