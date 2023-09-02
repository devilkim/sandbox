package com.devilkim.core.exception.http

abstract class ServerErrorException : HttpErrorException {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
