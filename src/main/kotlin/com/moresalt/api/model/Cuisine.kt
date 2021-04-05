package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "cuisine")
open class Cuisine
    (
    @Enumerated open var cuisine: Cuisine? = null,
    ): PanacheEntity() {

    enum class Cuisine {
        ITALIAN, INDIAN, BRITISH, AMERICAN;
    }
}
