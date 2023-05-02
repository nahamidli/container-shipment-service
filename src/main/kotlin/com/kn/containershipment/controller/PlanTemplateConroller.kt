package com.kn.containershipment.controller

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.service.PlanTemplateService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class TemplatePlanController(private val templatePlanService: PlanTemplateService) {

    @GetMapping("planTemplates/{id}")
    fun getPlanTemplate(@PathVariable id: Long) : PlanTemplate? {
        return templatePlanService.get(id)

    }

    @GetMapping("planTemplates")
    fun getAll(): List<PlanTemplate> {
        return templatePlanService.getAll() as List<PlanTemplate>
    }

    @GetMapping("planTemplates/default")
    fun getPlanTemplate() : PlanTemplate? {
        return templatePlanService.getDefault()

    }

}