package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "step")
open class Step
    (
    open var name: String = "",
    open var description: String = "",
    open var stepOrder: Long = 0,
    open var timeToComplete: Long = 0,
    @OneToMany open var ingredient: MutableSet<Ingredient> = mutableSetOf(),
    @OneToMany open var tools: MutableSet<Tool> = mutableSetOf(),
    @Transient open var dependsOnSteps: Set<String> = linkedSetOf(),
    ): PanacheEntity()





