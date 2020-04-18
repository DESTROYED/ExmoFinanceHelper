package com.destr.financehelper.presentation.common

import android.content.Context
import android.util.Log
import com.destr.apierrorhandler.ExceptionHandler

object ApiErrorsHandler: ExceptionHandler {

    private val TAG = javaClass.canonicalName

    // Used only Root Activity
    var context: Context? = null

    override fun showBadRequestError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showBadGatewayError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showForbiddenError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showInternalServerError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showInternetConnectionError(errorMessage: String) {
        Log.d(TAG, "INTERNET CONNECTION " + errorMessage)
    }

    override fun showMethodNotAllowedError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showNotFoundError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showServiceUnavailableError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun showUnauthorizedError(errorMessage: String) {
        Log.d(TAG, errorMessage)
    }

    override fun handleOtherError(errorMessage: String) {
        Log.d(TAG, "TEST" + errorMessage)
    }
}