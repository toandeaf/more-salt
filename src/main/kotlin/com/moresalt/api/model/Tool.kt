package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tool")
open class Tool
    (
    open var name: String = "",
    open var complexity: Int = 0,
    open var type: String? = null
    ): PanacheEntity()

