package com.devilkim.core.exception

import com.devilkim.core.exception.http.server.InternalServerErrorException


class NotImplementedException : InternalServerErrorException("구현이 되지 않은 메소드")
