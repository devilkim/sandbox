package com.devilkim.core.exception.http.client

import com.devilkim.core.exception.http.ClientErrorException

abstract class NotFoundException : ClientErrorException {
    constructor() : super("해당 리소스가 존재하지 않습니다.")
    constructor(message: String) : super(message)
}
