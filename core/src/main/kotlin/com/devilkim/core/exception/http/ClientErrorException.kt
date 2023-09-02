package com.devilkim.core.exception.http

abstract class ClientErrorException : HttpErrorException {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
