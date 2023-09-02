package com.devilkim.core.dto

data class BaseErrorResponseDto(val success: Boolean, val messages: String) {
    companion object {
        fun of(message: String): BaseErrorResponseDto {
            return BaseErrorResponseDto(false, message)
        }
    }
}
