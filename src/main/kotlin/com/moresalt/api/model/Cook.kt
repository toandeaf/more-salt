package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
@Table(name = "cook")
open class Cook
    (
    @ManyToOne open var recipeCooked: Recipe = Recipe(),
    @OneToOne open var feedback: CookFeedback? = null
    ): PanacheEntity()