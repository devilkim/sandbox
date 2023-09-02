package com.devilkim.core.exception.http

import com.devilkim.core.exception.BaseException

abstract class HttpErrorException : BaseException {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
