package com.universall.auth_api.domain.schemas

data class RegisterSchema(
    val login: String,
    val password: String,
    val username: String,
    val firstName: String,
    val lastName: String
)
