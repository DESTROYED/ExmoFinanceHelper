package com.destr.apierrorhandler

import com.destr.apierrorhandler.exception.*
import okhttp3.Response

fun Response.orThrow() =
    when (code) {
        400 -> throw BadRequestException
        401 -> throw UnauthorizedException
        403 -> throw ForbiddenException
        404 -> throw NotFoundException
        405 -> throw MethodNotAllowedException
        500 -> throw InternalServerErrorException
        502 -> throw BadGatewayException
        503 -> throw ServiceUnavailableException
        else -> this
    }