package com.universall.appcore.appcore.logs

import android.util.Log
import com.universall.appcore.BuildConfig

inline fun <reified T : Any> logTagFor(): String = T::class.simpleName ?: T::class.java.name

inline fun <reified T : Any> ifLoggable(level: Int, block: () -> Unit) {
    if (Log.isLoggable(logTagFor<T>(), level)) block()
}

inline fun <reified T : Any> ifDebugAndLoggable(level: Int, block: () -> Unit) {
    if (BuildConfig.DEBUG) ifLoggable<T>(level, block)
}

inline fun <reified T : Any> T.logDebug(crossinline message: () -> String) =
    ifLoggable<T>(Log.DEBUG) {
        Log.d(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logInfo(crossinline message: () -> String) =
    ifLoggable<T>(Log.INFO) {
        Log.i(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logWarn(crossinline message: () -> String) =
    ifLoggable<T>(Log.WARN) {
        Log.w(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logWarn(throwable: Throwable, crossinline message: () -> String) =
    ifLoggable<T>(Log.WARN) {
        Log.w(logTagFor<T>(), message(), throwable)
    }

inline fun <reified T : Any> T.logError(crossinline message: () -> String) =
    ifLoggable<T>(Log.ERROR) {
        Log.e(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logError(throwable: Throwable, crossinline message: () -> String) =
    ifLoggable<T>(Log.ERROR) {
        Log.e(logTagFor<T>(), message(), throwable)
    }

inline fun <reified T : Any> T.logDebugDev(crossinline message: () -> String) =
    ifDebugAndLoggable<T>(Log.DEBUG) {
        Log.d(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logInfoDev(crossinline message: () -> String) =
    ifDebugAndLoggable<T>(Log.INFO) {
        Log.i(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logWarnDev(crossinline message: () -> String) =
    ifDebugAndLoggable<T>(Log.WARN) {
        Log.w(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logWarnDev(throwable: Throwable, crossinline message: () -> String) =
    ifDebugAndLoggable<T>(Log.WARN) {
        Log.w(logTagFor<T>(), message(), throwable)
    }

inline fun <reified T : Any> T.logErrorDev(crossinline message: () -> String) =
    ifDebugAndLoggable<T>(Log.ERROR) {
        Log.e(logTagFor<T>(), message())
    }

inline fun <reified T : Any> T.logErrorDev(throwable: Throwable, crossinline message: () -> String) =
    ifDebugAndLoggable<T>(Log.ERROR) {
        Log.e(logTagFor<T>(), message(), throwable)
    }
