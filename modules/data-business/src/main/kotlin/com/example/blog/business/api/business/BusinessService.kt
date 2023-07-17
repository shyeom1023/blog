package com.example.blog.business.api.business

import com.example.blog.business.component.data.BusinessClient
import com.example.blog.business.component.data.dto.BusinessStatusResponse
import com.example.blog.business.component.data.dto.BusinessStatusSearch
import org.springframework.stereotype.Service

@Service
class BusinessService(
    private val businessClient: BusinessClient
) {

    fun status(request: BusinessStatusSearch): BusinessStatusResponse {
        return businessClient.status(request)
    }
}