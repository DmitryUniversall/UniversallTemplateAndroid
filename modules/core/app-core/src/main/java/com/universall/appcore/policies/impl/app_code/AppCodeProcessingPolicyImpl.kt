package com.universall.appcore.policies.impl.app_code

import com.universall.core.network.exceptions.AlreadyExistsHttpException
import com.universall.core.network.exceptions.AuthExpiredHttpException
import com.universall.core.network.exceptions.BadRequestHttpException
import com.universall.core.network.exceptions.InternalServerErrorHttpException
import com.universall.core.network.exceptions.NotFoundHttpException
import com.universall.core.network.exceptions.UnknownHttpException
import com.universall.core.policies.app_code.AppCodeProcessingPolicy
import io.ktor.http.HttpStatusCode

class AppCodeProcessingPolicyImpl : AppCodeProcessingPolicy {
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
