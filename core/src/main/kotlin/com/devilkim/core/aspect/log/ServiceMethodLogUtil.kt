package com.devilkim.core.aspect.log

import org.aspectj.lang.reflect.MethodSignature

class ServiceMethodLogUtil {

    companion object {
        fun createMethodMessage(type: String, className: String, methodName: String, message: String?): String {
            return "![$type]$className.$methodName() $message"
        }

        fun createKeyValue(key: String, value: String?): String {
            return "$key: $value | "
        }

        fun createParametersInfoMessage(methodSignature: MethodSignature, args: Array<Any?>): String {
            val stringBuilder = StringBuilder()
            for (i in methodSignature.parameterNames.indices) {
                stringBuilder
                        .append(methodSignature.parameterNames[i])
                        .append("(")
                        .append(methodSignature.parameterTypes[i].getSimpleName())
                        .append("):")
                        .append(args[i])
                        .append(", ")
            }
            return stringBuilder.toString()
        }
    }
}
