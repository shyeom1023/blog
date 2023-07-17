package com.example.blog.business.component.data

import com.example.blog.business.component.data.dto.BusinessStatusResponse
import com.example.blog.business.component.data.dto.BusinessStatusSearch
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class BusinessClient(
    private val restTemplate: RestTemplate,
    private val uriHelper: BusinessUriHelper
) {

    fun status(request: BusinessStatusSearch): BusinessStatusResponse {
        return restTemplate.exchange(
            uriHelper.status,
            HttpMethod.POST,
            HttpEntity(request),
            BusinessStatusResponse::class.java,
            "DeWJHqEsVtjzPtqohuwllnmMfydZWywO0NnMsBrUi702kqjQ0qOghUZWXlRlsAgOBPMCwUxs5iQZb1qUM3ZPpA=="
        ).body!!
    }

}