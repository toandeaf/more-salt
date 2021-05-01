package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

//@Entity
//@Table(name = "recipe")
data class Recipe
    (
    var name: String = "",
    @Transient var ingredients: MutableSet<Ingredient> = mutableSetOf(),
    @Transient var tools: MutableSet<Tool> = mutableSetOf(),
    @OneToMany var steps: MutableSet<Step> = mutableSetOf(),
    @OneToMany var cuisine: MutableSet<Cuisine> = mutableSetOf()
    ) : PanacheEntity()




