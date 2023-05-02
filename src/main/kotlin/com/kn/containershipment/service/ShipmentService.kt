package com.kn.containershipment.service

import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.ShipmentRepository
import org.springframework.stereotype.Service

@Service

class ShipmentService(private val shipmentRepository: ShipmentRepository) {
    fun getAll(): List<Shipment> {
        return shipmentRepository.findAll() as List<Shipment>
    }
    fun save(shipment: Shipment): Shipment {
        return shipmentRepository.save(shipment)
    }
}