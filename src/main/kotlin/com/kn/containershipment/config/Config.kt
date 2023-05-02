package com.kn.containershipment.config

import com.kn.containershipment.model.Action
import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.model.TemperatureRange
import com.kn.containershipment.repository.ActionRepository
import com.kn.containershipment.repository.TemperatureRangeRepository
import com.kn.containershipment.repository.TemplateRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Config {

    @Bean("myConfig")
    @Throws(Exception::class)
    fun run(templateRepository: TemplateRepository,
            actionRepository: ActionRepository,
            temperatureRangeRepository: TemperatureRangeRepository,
    ): CommandLineRunner {
        return CommandLineRunner {

            // create temperature
            val temperatureRange = temperatureRangeRepository.save(TemperatureRange(id = 1, min = -20, max = -10))
            // create actions
            val actions = actionRepository.saveAll(
                    mutableListOf(Action(id = 1, name = "shipment is taken from customer"),
                            Action(id = 2, name = "shipment is on the way"),
                            Action(id = 3, name = "shipment is arrived to destination"),
                            Action(id = 4, name = "shipment is handover to the destination target")))
                    .toMutableList()

            // create actions
            val actions2 = actionRepository.saveAll(
                    mutableListOf(Action(id = 5, name = "shipment is taken from customer"),
                            Action(id = 6, name = "shipment is on the way"),
                            Action(id = 7, name = "shipment is arrived to destination"),
                            Action(id = 8, name = "shipment is handover to the destination target")))
                    .toMutableList()


            // create default plan template
            val savedTemplate = templateRepository.save(
                    PlanTemplate(
                            id = 1,
                            name = "General Shipment Template",
                            temperature = temperatureRange,
                            actions = actions
                    )
            )

                templateRepository.save(
                    PlanTemplate(
                            id = 2,
                            name = "General Shipment Template my example",
                            temperature = temperatureRange,
                            actions = actions2
                    )
            )

            println(savedTemplate)
        }
    }

}