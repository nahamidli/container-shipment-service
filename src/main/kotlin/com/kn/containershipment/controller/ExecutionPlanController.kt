package com.kn.containershipment.controller

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.service.ExecutionPlanService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("execution-plans")
@CrossOrigin
class ExecutionPlanController(private val executionPlanService: ExecutionPlanService) {
    @GetMapping
    fun getAll(): List<ExecutionPlan> {
        return executionPlanService.getAll()
    }
    @PostMapping
    fun save(@RequestBody executionPlan: ExecutionPlan): ExecutionPlan {
        return executionPlanService.save(executionPlan)
    }

}