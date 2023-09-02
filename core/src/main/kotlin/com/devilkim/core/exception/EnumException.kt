package com.devilkim.core.exception

import com.devilkim.core.exception.http.client.BadRequestException
import com.devilkim.core.exception.http.server.InternalServerErrorException

class EnumException {
    class EnumConvertServerException : InternalServerErrorException()
    open class InvalidEnumIdConvertException(message: String) : BadRequestException(message)
}