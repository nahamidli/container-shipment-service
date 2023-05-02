package com.kn.containershipment.controller

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.ShipmentRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ShipmentController(private val shipmentRepository: ShipmentRepository) {

//    @GetMapping("shipments/{id}")
//    fun getPlanTemplate(@PathVariable id: Long) : PlanTemplate? {
//        return templatePlanService.get(id)
//
//    }
//
    @GetMapping("shipments")
    fun getAll(): List<Shipment> {
        return shipmentRepository.findAll() as List<Shipment>
    }
//
//    @GetMapping("shipments/default")
//    fun getPlanTemplate() : PlanTemplate? {
//        return templatePlanService.getDefault()
//
//    }

}