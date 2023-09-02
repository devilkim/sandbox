package com.devilkim.core.exception.http.client

import com.devilkim.core.exception.http.ClientErrorException

abstract class BadRequestException : ClientErrorException {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
