package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Table

//@Entity
//@Table(name = "tool")
data class Tool
    (
    var name: String = "",
    var complexity: Int = 0,
    var type: String = ""
    ): PanacheEntity()

