package com.universall.appcore.policies.app_code

import com.universall.appcore.network.exceptions.AlreadyExistsHttpException
import com.universall.appcore.network.exceptions.AuthExpiredHttpException
import com.universall.appcore.network.exceptions.BadRequestHttpException
import com.universall.appcore.network.exceptions.InternalServerErrorHttpException
import com.universall.appcore.network.exceptions.NotFoundHttpException
import com.universall.appcore.network.exceptions.UnknownHttpException
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

class AppCodeProcessingPolicyImpl @Inject constructor() : AppCodeProcessingPolicy {
    override fun process(httpStatusCode: HttpStatusCode, appCode: Int, message: String) {
        if (appCode in 1000..1999) return

        when (appCode) {
            2000 -> throw InternalServerErrorHttpException(message, appCode, httpStatusCode)
            2001 -> throw AlreadyExistsHttpException(message, appCode, httpStatusCode)
            2002 -> throw NotFoundHttpException(message, appCode, httpStatusCode)
            2003 -> throw BadRequestHttpException(message, appCode, httpStatusCode)
            in 3005..3007, in 3010..3013 -> throw AuthExpiredHttpException(message, appCode, httpStatusCode)
            else -> throw UnknownHttpException(message, appCode, httpStatusCode)
        }
    }
}
