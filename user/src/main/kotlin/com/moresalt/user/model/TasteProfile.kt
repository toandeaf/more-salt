package com.moresalt.user.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "taste_profile")
open class TasteProfile
    (
    open var sweetness: Int = 3,
    open var bitterness: Int = 3
    ): PanacheEntity() {
        companion object {
            fun parseFromGrpc(): TasteProfile {
                return TasteProfile(
                    sweetness = 3,
                    bitterness = 3
                )
            }
        }
    }
