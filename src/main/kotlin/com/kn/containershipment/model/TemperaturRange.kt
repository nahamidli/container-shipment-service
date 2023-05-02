package com.kn.containershipment.model

import jakarta.persistence.*

@Entity
@Table
data class TemperatureRange(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        val min: Int = 0,
        val max: Int = 0
)