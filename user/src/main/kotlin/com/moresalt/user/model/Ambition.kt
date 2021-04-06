package com.moresalt.user.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

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
        fun parseFromGrpc(): Ambition {
            return Ambition(
                instructions = "HEAVY",
                variety = "VARIOUS",
                complexity = "COMPLEX",
                social = "SOCIAL"
            )
        }
    }
}


