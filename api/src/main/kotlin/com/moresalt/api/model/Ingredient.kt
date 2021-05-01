package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

//@Entity
//@Table(name = "ingredient")
data class Ingredient
    (
    var name: String = "",
    ): PanacheEntity()
