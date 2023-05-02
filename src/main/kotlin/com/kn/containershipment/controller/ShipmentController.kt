package com.kn.containershipment.controller

import com.kn.containershipment.model.Shipment
import com.kn.containershipment.service.ShipmentService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ShipmentController(private val shipmentService: ShipmentService) {

    @GetMapping("shipments")
    fun getAll(): List<Shipment> {
        return shipmentService.getAll() as List<Shipment>
    }
}