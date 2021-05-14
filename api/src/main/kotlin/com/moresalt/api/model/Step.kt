package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*
import kotlin.jvm.Transient

//@Entity
//@Table(name = "step")
data class Step
    (
    var name: String = "",
    var description: String = "",
    var stepOrder: Long = 0,
    var timeToComplete: Long = 0,
    @OneToMany var ingredient: MutableSet<Ingredient> = mutableSetOf(),
    @OneToMany var tools: MutableSet<Tool> = mutableSetOf(),
    @Transient var dependsOnSteps: Set<String> = linkedSetOf(),
    ): PanacheEntity()





