package com.universall.auth_api.domain.schemas

data class RegisterSchema(
    val firstName: String,
    val lastName: String,
    val username: String,
    val login: String,
    val password: String
)
