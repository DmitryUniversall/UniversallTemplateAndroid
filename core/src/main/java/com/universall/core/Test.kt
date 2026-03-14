package com.universall.core

import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val id: Int,
    val username: String
)
