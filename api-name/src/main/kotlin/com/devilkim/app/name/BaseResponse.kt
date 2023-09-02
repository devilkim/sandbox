package com.devilkim.app.name

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponseDto<T>(val success: Boolean, val data: T?, val message: String?) {

    companion object {
        fun ok(): BaseResponseDto<*> {
            return BaseResponseDto<Any?>(true, null, null)
        }

        fun <T> ok(data: T): BaseResponseDto<T> {
            return BaseResponseDto(true, data, null)
        }
    }

}
