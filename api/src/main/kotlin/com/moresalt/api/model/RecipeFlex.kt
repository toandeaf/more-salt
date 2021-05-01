package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

//@Entity
//@Table(name = "recipe_flex")
data class RecipeFlex
    (
    @OneToOne var baseRecipe: Recipe = Recipe(),
    @OneToMany var steps: MutableSet<Step> = mutableSetOf()
    ): PanacheEntity()