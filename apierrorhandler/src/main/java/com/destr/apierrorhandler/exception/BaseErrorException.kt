package com.destr.apierrorhandler.exception

open class BaseErrorException(code: Int, message: String?) : Throwable("$code: $message")