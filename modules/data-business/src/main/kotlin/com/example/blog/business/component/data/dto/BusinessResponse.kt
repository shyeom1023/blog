package com.example.blog.business.component.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BusinessStatusResponse(

    @JsonProperty("request_cnt")
    val requestCnt: Int,
    @JsonProperty("status_code")
    val statusCode: String,
    @JsonProperty("data")
    val data: List<DataResponse>
)

data class DataResponse(
    @JsonProperty("b_no")
    val bNo: String,
    @JsonProperty("b_stt")
    val bStt: String,
    @JsonProperty("b_stt_cd")
    val bSttCd: String,
    @JsonProperty("tax_type")
    val taxType: String,
    @JsonProperty("tax_type_cd")
    val taxTypeCd: String,
    @JsonProperty("end_dt")
    val endDt: String,
    @JsonProperty("utcc_yn")
    val utccYn: String,
    @JsonProperty("tax_type_change_dt")
    val taxTypeChangeDt: String,
    @JsonProperty("invoice_apply_dt")
    val invoiceApplyDt: String,
    @JsonProperty("rbf_tax_type")
    val rbfTaxType: String,
    @JsonProperty("rbf_tax_type_cd")
    val rbfTaxTypeCd: String
)


