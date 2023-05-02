package com.kn.containershipment

import com.kn.containershipment.config.Config
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.model.TemperatureRange
import com.kn.containershipment.repository.ShipmentRepository
import com.kn.containershipment.repository.TemperatureRangeRepository
import com.kn.containershipment.service.ExecutionPlanService
import com.kn.containershipment.service.PlanTemplateService
import com.kn.containershipment.service.TemperatureRangeService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
//import org.springframework.amqp.rabbit.annotation.RabbitListener


@Component
//@DependsOn("myConfig")

class ShipmentMessageListener(private val planTemplateService: PlanTemplateService,
                              private val executionPlanService: ExecutionPlanService,
                              private val shipmentRepository: ShipmentRepository,
                              private val temperatureRangeService: TemperatureRangeService,
                              private val config: Config) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["shipment"])
    fun receiveMessage(shipment: Shipment) {

        logger.info("Received shipment: $shipment")

        // Create execution plan object and save it to database
        createExecutionPlan(shipment)

    }

    private fun createExecutionPlan(shipment: Shipment) {
        val template = planTemplateService.getDefault() ?: return

        //val temperatureRangeId = temperatureRangeService.save(shipment.temperature!!)

        // Create the Execution Plan object by merging the Shipment and Template objects
        val executionPlan = ExecutionPlan(
                id = shipment.id,
                origin = shipment.origin,
                destination = shipment.destination,
                customerId = shipment.customerId,
                transportType = shipment.transportType,
                temperature =
                TemperatureRange(
                        max = shipment.temperature!!.max,
                        min = shipment.temperature.min),
                fragile = shipment.fragile,
                notifyCustomer = shipment.notifyCustomer,
                templateId = template!!.id,
                executionPlanActions = template.actions.map {
                    ExecutionPlanAction(
                            actionName = it.name,
                            isExecuted = true,
                            isNotify = false
                    )
                } as MutableList<ExecutionPlanAction>
        )

        logger.info("Execution Plan created and successfully saved to DB: $executionPlan")
        executionPlanService.save(executionPlan)
        shipmentRepository.save(shipment)

    }
}