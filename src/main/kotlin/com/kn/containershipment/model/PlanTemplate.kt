package com.kn.containershipment.model

import jakarta.persistence.*

@Entity
@Table(name = "plan_template")
data class PlanTemplate(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    val name: String? = null,

    @OneToMany(targetEntity = Action::class, cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "pta_fk", referencedColumnName = "id")
    val actions: MutableList<Action> = ArrayList(),

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "fk_temperature_range_id")
    val temperature: TemperatureRange? = null
)



@Entity
@Table
data class Action(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val name: String? = null,
)