package com.devilkim.core.aspect.log

import com.devilkim.core.exception.AspectException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.text.DecimalFormat

@Aspect
@Component
class ServiceMethodLogAdvice {
    companion object {
        private val DECIMAL_FORMAT: DecimalFormat = DecimalFormat("###,###ms")

        private val logger = LoggerFactory.getLogger("ServiceMethod Log")
    }
    @Throws(Throwable::class)
    private fun printLog(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature
        val runningClass = signature.declaringType
        val runningMethodName = signature.name
        val args: Array<Any?> = joinPoint.args
        return if (signature !is MethodSignature) {
            throw AspectException.AspectMethodSignatureCastException()
        } else {
            val parameterMessage = ServiceMethodLogUtil.createKeyValue("Parameters", ServiceMethodLogUtil.createParametersInfoMessage(signature, args))
            val beforeMessage = ServiceMethodLogUtil.createMethodMessage("Ready", runningClass.getSimpleName(), runningMethodName, parameterMessage)
            logger.info(beforeMessage)
            val beforeTime = System.currentTimeMillis()
            val result: Any? = joinPoint.proceed()
            val afterTime = System.currentTimeMillis()
            val processTime = afterTime - beforeTime
            val processTimeMessage = "- " + DECIMAL_FORMAT.format(processTime) + " | "
            val returnValueMessage = ServiceMethodLogUtil.createKeyValue("Return", result?.toString() ?: "void")
            val completeMessage = ServiceMethodLogUtil.createMethodMessage("Done", runningClass.getSimpleName(), runningMethodName, processTimeMessage + returnValueMessage)
            logger.info(completeMessage)
            result
        }
    }

    @Around("@annotation(com.devilkim.core.aspect.log.ServiceMethod)")
    @Throws(Throwable::class)
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        return printLog(joinPoint)
    }
}
