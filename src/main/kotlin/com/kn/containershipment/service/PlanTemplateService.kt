package com.kn.containershipment.service

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.repository.TemplateRepository
import org.springframework.stereotype.Service


@Service
class PlanTemplateService(private val templateRepository: TemplateRepository) {

    fun save(template: PlanTemplate) : Long {
        val savedTemplate = templateRepository.save(template)
        return savedTemplate.id
    }

    fun get(id: Long) : PlanTemplate? {
        return templateRepository.findById(id).orElse(null)
    }

    fun getAll(): List<PlanTemplate> {
        return templateRepository.findAll() as List<PlanTemplate>
    }

    fun getDefault() : PlanTemplate? {
        return get(1)
    }
}