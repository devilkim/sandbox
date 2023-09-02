package com.devilkim.core.handler

import com.devilkim.core.dto.BaseErrorResponseDto
import com.devilkim.core.exception.http.client.BadRequestException
import com.devilkim.core.exception.http.client.NotFoundException
import com.devilkim.core.exception.http.client.UnauthorizedException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.format.DateTimeParseException
import java.util.*

@RestControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }
    @ExceptionHandler(Exception::class)
    protected fun handleException(ex: Exception): ResponseEntity<BaseErrorResponseDto> {
        ex.printStackTrace()
        logger.error(HttpStatus.INTERNAL_SERVER_ERROR.toString() + ", " + ex.message)
        return ResponseEntity
                .internalServerError()
                .body(BaseErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR.toString()))
    }

    @ExceptionHandler(UnauthorizedException::class)
    protected fun handleUnauthorizedException(ex: UnauthorizedException): ResponseEntity<BaseErrorResponseDto> {
        ex.printStackTrace()
        logger.error(HttpStatus.UNAUTHORIZED.toString() + ", " + ex.message)
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build()
    }

    @ExceptionHandler(BadRequestException::class)
    protected fun handleBadRequestException(ex: BadRequestException): ResponseEntity<BaseErrorResponseDto> {
        ex.printStackTrace()
        logger.error(HttpStatus.BAD_REQUEST.toString() + ", " + ex.message)
        return ResponseEntity
                .badRequest()
                .body(BaseErrorResponseDto.of(ex.message!!))
    }

    @ExceptionHandler(NotFoundException::class)
    protected fun handleNotFoundException(ex: NotFoundException): ResponseEntity<BaseErrorResponseDto> {
        ex.printStackTrace()
        logger.error(HttpStatus.NOT_FOUND.toString() + ", " + ex.message)
        return ResponseEntity
                .notFound()
                .build()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<BaseErrorResponseDto> {
        val bindingResult: BindingResult = ex.bindingResult
        val builder = StringBuilder()
        for (fieldError in bindingResult.fieldErrors) {
            builder.append("'")
                    .append(fieldError.field)
                    .append("'(은)는 ")
                    .append(fieldError.defaultMessage)
                    .append(". 입력된 값: '")
                    .append(fieldError.rejectedValue)
                    .append("'")
        }
        return handleBadRequestException(object : BadRequestException(builder.toString()) {})
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<BaseErrorResponseDto> {
        return when (ex.cause) {
            is UnrecognizedPropertyException -> {
                val unrecognizedPropertyException = ex.cause as UnrecognizedPropertyException
                val unrecognizedProperty = unrecognizedPropertyException.propertyName
                handleBadRequestException(object : BadRequestException("불필요한 파라미터($unrecognizedProperty)", ex) {})
            }
            is InvalidFormatException -> {
                val invalidFormatException = ex.cause as InvalidFormatException
                val type: Array<out Any> = invalidFormatException.targetType
                        .getEnumConstants()
                val typeListString = Arrays.stream(type)
                        .map { obj: Any -> obj.toString() }
                        .reduce { s1: String, s2: String -> "$s1','$s2" }
                        .orElse("")
                val value: String = invalidFormatException.value
                        .toString()
                val field: String = invalidFormatException.getPath()[0]
                        .fieldName
                val message = String.format("'%s'(은)는 '%s' 값만 입력 가능(입력된 값: '%s')", field, typeListString, value)
                handleBadRequestException(object : BadRequestException(message, ex) {})
            }
            is InvalidTypeIdException -> {
                val message = "Body 변환에 실패"
                handleBadRequestException(object : BadRequestException(message, ex) {})
            }
            else -> {
                val message = "Body 가 누락 되었거나, 형식이 올바르지 않음."
                handleBadRequestException(object : BadRequestException(message, ex) {})
            }
        }
    }

    @ExceptionHandler
    protected fun handleDateTimeParseException(ex: DateTimeParseException): ResponseEntity<BaseErrorResponseDto> {
        return handleBadRequestException(object : BadRequestException("DateTime 포맷이 유효하지 않음", ex) {})
    }
}