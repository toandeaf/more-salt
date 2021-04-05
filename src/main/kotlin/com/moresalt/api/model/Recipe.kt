package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "recipe")
open class Recipe
    (
    open var name: String = "",
    @Transient open var ingredients: MutableSet<Ingredient> = mutableSetOf(),
    @Transient open var tools: MutableSet<Tool> = mutableSetOf(),
    @OneToMany open var steps: MutableSet<Step> = mutableSetOf(),
    @OneToMany open var cuisine: MutableSet<Cuisine> = mutableSetOf()
    ) : PanacheEntity()




