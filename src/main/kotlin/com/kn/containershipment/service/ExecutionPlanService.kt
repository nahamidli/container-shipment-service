package com.kn.containershipment.service

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.repository.ExecutionPlanRepository
import org.springframework.stereotype.Service

@Service

class ExecutionPlanService (private val executionPlanRepository: ExecutionPlanRepository){

    fun save(executionPlan: ExecutionPlan): ExecutionPlan {
        return executionPlanRepository.save(executionPlan)
    }

    fun getAll(): List<ExecutionPlan> {
        return executionPlanRepository.findAll() as List<ExecutionPlan>
    }
}