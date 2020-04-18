package com.destr.apierrorhandler

import com.destr.apierrorhandler.exception.*

class ApiUncaughtExceptionHandler(private val handler: ExceptionHandler) :
    Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, error: Throwable) {
        val message = error.message.orEmpty()
        when (error.cause) {
            is BadRequestException -> handler.showBadRequestError(message)
            is UnauthorizedException -> handler.showUnauthorizedError(message)
            is ForbiddenException -> handler.showForbiddenError(message)
            is NotFoundException -> handler.showNotFoundError(message)
            is MethodNotAllowedException -> handler.showMethodNotAllowedError(message)
            is InternalServerErrorException -> handler.showInternalServerError(message)
            is BadGatewayException -> handler.showBadGatewayError(message)
            is ServiceUnavailableException -> handler.showServiceUnavailableError(message)
            is InternetConnectionException -> handler.showInternetConnectionError(message)
            else -> handler.handleOtherError(message)
        }
    }
}