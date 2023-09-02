package com.devilkim.core.exception.http.client

import com.devilkim.core.exception.http.ClientErrorException

abstract class UnauthorizedException : ClientErrorException("UnauthorizedException")
