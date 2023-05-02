package com.kn.containershipment.service

import com.kn.containershipment.model.TemperatureRange
import com.kn.containershipment.repository.TemperatureRangeRepository
import org.springframework.stereotype.Service

@Service
class TemperatureRangeService(private val temperatureRangeRepository: TemperatureRangeRepository) {
    fun save(temperatureRange: TemperatureRange) : Long {
        val savedTemperatureRange = temperatureRangeRepository.save(temperatureRange)
        return savedTemperatureRange.id
    }
}


