package com.example.blog.business.component.data

class BusinessUriHelper(
    endpoint: String
) {

    private val business = "/nts-businessman/v1"

    val status = "$endpoint$business/status?serviceKey={serviceKey}"
}