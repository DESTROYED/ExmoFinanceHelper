package com.destr.financehelper.presentation.common

import android.util.Log
import android.view.View
import com.destr.apierrorhandler.ExceptionHandler
import com.destr.financehelper.presentation.util.showInfoSnackbar

object ApiErrorsHandler: ExceptionHandler {

    private val TAG = javaClass.canonicalName

    // Used only Root Activity
    var view: View? = null

    override fun showBadRequestError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Bad Request")
    }

    override fun showBadGatewayError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Bad Gateway")
    }

    override fun showForbiddenError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Forbidden")
    }

    override fun showInternalServerError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Internal Server problem")
    }

    override fun showInternetConnectionError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Internet Connection problem")
    }

    override fun showMethodNotAllowedError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Method Not Allowed")
    }

    override fun showNotFoundError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Not Found")
    }

    override fun showServiceUnavailableError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Service Unavailable")
    }

    override fun showUnauthorizedError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Unauthorized")
    }

    override fun handleOtherError(errorMessage: String) {
        Log.d(TAG, errorMessage)
        view?.showInfoSnackbar("Unknown error")
    }
}