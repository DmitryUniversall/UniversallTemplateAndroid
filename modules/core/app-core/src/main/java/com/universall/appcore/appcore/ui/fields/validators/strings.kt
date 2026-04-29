package com.universall.appcore.appcore.ui.fields.validators

import com.universall.appcore.R
import com.universall.appcore.base.ui.fields.FieldState
import com.universall.appcore.appcore.ui.utils.UIString

fun FieldState.Builder<String>.validateStringLength(minLength: Long? = null, maxLength: Long? = null): UIString? {
    if (value.isEmpty()) return UIString.resource(R.string.empty_field_error)
    if (minLength != null && value.length < minLength) return UIString.resource(R.string.value_too_short_error)
    if (maxLength != null && value.length > maxLength) return UIString.resource(R.string.value_too_long_error)
    return null
}
