package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.Table

//@Entity
//@Table(name = "cook_feedback")
data class CookFeedback
    (
    @OneToOne var cook: Cook? = null
    ): PanacheEntity()
