package com.devilkim.core.exception.http.server

import com.devilkim.core.exception.http.ServerErrorException

abstract class InternalServerErrorException : ServerErrorException {
    constructor() : super("Internal Server Error")
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
    constructor(throwable: Throwable) : super("", throwable)
}