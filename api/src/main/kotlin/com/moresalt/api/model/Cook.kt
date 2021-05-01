package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

//@Entity
//@Table(name = "cook")
data class Cook
    (
    @ManyToOne var recipeCooked: Recipe = Recipe(),
    @OneToOne var feedback: CookFeedback? = null
    ): PanacheEntity()