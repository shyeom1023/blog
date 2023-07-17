package com.example.blog.business.api

import com.example.blog.business.api.business.BusinessService
import com.example.blog.business.component.data.dto.BusinessStatusResponse
import com.example.blog.business.component.data.dto.BusinessStatusSearch
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/business/internal/business")
class InternalBusinessControllerV1(
    private val businessService: BusinessService
) {

    @PostMapping("/status")
    fun status(@RequestBody request: BusinessStatusSearch): BusinessStatusResponse {
        return businessService.status(request)
    }
}