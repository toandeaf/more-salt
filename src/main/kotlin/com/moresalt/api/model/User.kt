package com.moresalt.api.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "usr")
open class User
    (
    open var username: String = "",
    open var email: String = "",
    open var password: String = "",
    @OneToOne(cascade = [CascadeType.ALL]) open var tasteProfile: TasteProfile? = null,
    @OneToOne(cascade = [CascadeType.ALL]) open var ambitions: Ambition? = null
    ): PanacheEntity()