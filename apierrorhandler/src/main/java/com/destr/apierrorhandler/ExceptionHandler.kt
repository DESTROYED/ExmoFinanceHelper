package com.destr.apierrorhandler

interface ExceptionHandler {

    fun showBadRequestError(errorMessage: String)

    fun showBadGatewayError(errorMessage: String)

    fun showForbiddenError(errorMessage: String)

    fun showInternalServerError(errorMessage: String)

    fun showInternetConnectionError(errorMessage: String)

    fun showMethodNotAllowedError(errorMessage: String)

    fun showNotFoundError(errorMessage: String)

    fun showServiceUnavailableError(errorMessage: String)

    fun showUnauthorizedError(errorMessage: String)

    fun handleOtherError(errorMessage: String)
}