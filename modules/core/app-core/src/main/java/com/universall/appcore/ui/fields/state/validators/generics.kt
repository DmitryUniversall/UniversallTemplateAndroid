package com.universall.appcore.ui.fields.state.validators

import com.universall.appcore.R
import com.universall.appcore.ui.fields.state.FieldState
import com.universall.appcore.utils.UIString

fun FieldState.Builder<String>.validateStringLength(minLength: Long? = null, maxLength: Long? = null): UIString? {
    if (value.isEmpty()) return UIString.resource(R.string.empty_field_error)
    if (minLength != null && value.length < minLength) return UIString.resource(R.string.value_too_short_error)
    if (maxLength != null && value.length > maxLength) return UIString.resource(R.string.value_too_long_error)
    return null
}
