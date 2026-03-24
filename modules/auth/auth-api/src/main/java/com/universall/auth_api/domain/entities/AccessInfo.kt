package com.universall.auth_api.domain.entities

import com.universall.core.collections.annotations.ConventionImmutable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@ConventionImmutable
data class AccessInfo(
    val authorities: PersistentList<Authority> = persistentListOf(),
    val roles: PersistentList<Role> = persistentListOf()
)
