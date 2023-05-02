package com.kn.containershipment

import com.kn.containershipment.model.*
import com.kn.containershipment.service.ExecutionPlanService
import com.kn.containershipment.service.PlanTemplateService
import com.kn.containershipment.service.ShipmentService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import com.kn.containershipment.model.TemperatureRange
import java.util.*


@ExtendWith(MockitoExtension::class)
@ExtendWith(MockitoExtension::class, SpringExtension::class)
@SpringBootTest

class ShipmentMessageListenerTest {

    @Mock
    private lateinit var planTemplateService: PlanTemplateService

    @Mock
    private lateinit var executionPlanService: ExecutionPlanService

    @Mock
    private lateinit var shipmentService: ShipmentService

    @InjectMocks
    private lateinit var shipmentMessageListener: ShipmentMessageListener
   // private val shipmentMessageListener = ShipmentMessageListener(planTemplateService, executionPlanService, shipmentService)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        shipmentMessageListener = ShipmentMessageListener(planTemplateService, executionPlanService, shipmentService)
    }

    @Test
    fun testReceiveMessage() {
        val shipment = Shipment(
                id = 1,
                origin = "New York",
                destination = "Los Angeles",
                customerId = "67890",
                transportType = TransportType.AIR,
                temperature = TemperatureRange(min = 10, max = 20),
                fragile = false,
                notifyCustomer = true
        )

        val planTemplate = PlanTemplate(
                id = 1,
                actions = mutableListOf(Action(name = "Action 1"), Action(name = "Action 2")))

        val executionPlan =  ExecutionPlan(
                id = shipment.id,
                origin = shipment.origin,
                destination = shipment.destination,
                customerId = shipment.customerId,
                transportType = shipment.transportType,
                temperature = shipment.temperature,
                fragile = shipment.fragile,
                notifyCustomer = shipment.notifyCustomer,
                templateId = planTemplate.id,
                executionPlanActions = planTemplate.actions.map {
                    ExecutionPlanAction(
                            actionName = it.name,
                            isExecuted = true,
                            isNotify = false
                    )
                } as MutableList<ExecutionPlanAction>
        )


        `when`(planTemplateService.getDefault()).thenReturn(planTemplate)


        // Act
        shipmentMessageListener.receiveMessage(shipment)


        // Assert
        verify(shipmentService, times(1)).save(shipment)
        verify(executionPlanService, times(1)).save(executionPlan)
    }
}