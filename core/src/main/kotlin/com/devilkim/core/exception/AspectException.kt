package com.devilkim.core.exception

import com.devilkim.core.exception.http.server.InternalServerErrorException

class AspectException {
    class AspectMethodSignatureCastException : InternalServerErrorException("AOP 내에 Signature 를 MethodSignature 로 캐스팅 하는데 실패")
}
