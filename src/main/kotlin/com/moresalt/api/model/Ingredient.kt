package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "ingredient")
open class Ingredient
    (
    open var name: String = "",
    ): PanacheEntity()
