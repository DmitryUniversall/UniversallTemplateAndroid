package com.universall.auth_impl.domain.di

import com.universall.appcore.network.api.base.CoreApiClient
import com.universall.auth_api.domain.di.qualifiers.AuthenticatedApiClient
import com.universall.auth_api.domain.usecases.GetActiveSessionsUseCase
import com.universall.auth_api.domain.usecases.GetMeUseCase
import com.universall.auth_api.domain.usecases.GetTokenPairUseCase
import com.universall.auth_api.domain.usecases.LocalLogoutUseCase
import com.universall.auth_api.domain.usecases.LoginUseCase
import com.universall.auth_api.domain.usecases.LogoutUseCase
import com.universall.auth_api.domain.usecases.RefreshUseCase
import com.universall.auth_api.domain.usecases.RegisterUseCase
import com.universall.auth_api.domain.usecases.RestoreAuthStateUseCase
import com.universall.auth_api.domain.usecases.RevokeAllSessionsExceptCurrentUseCase
import com.universall.auth_api.domain.usecases.RevokeSessionUseCase
import com.universall.auth_api.domain.usecases.UpdateTokenPairUseCase
import com.universall.auth_impl.domain.usecases.GetActiveSessionsUseCaseImpl
import com.universall.auth_impl.domain.usecases.GetMeUseCaseImpl
import com.universall.auth_impl.domain.usecases.GetTokenPairUseCaseImpl
import com.universall.auth_impl.domain.usecases.LocalLogoutUseCaseImpl
import com.universall.auth_impl.domain.usecases.LoginUseCaseImpl
import com.universall.auth_impl.domain.usecases.LogoutUseCaseImpl
import com.universall.auth_impl.domain.usecases.RefreshUseCaseImpl
import com.universall.auth_impl.domain.usecases.RegisterUseCaseImpl
import com.universall.auth_impl.domain.usecases.RestoreAuthStateUseCaseImpl
import com.universall.auth_impl.domain.usecases.RevokeAllSessionsExceptCurrentUseCaseImpl
import com.universall.auth_impl.domain.usecases.RevokeSessionUseCaseImpl
import com.universall.auth_impl.domain.usecases.UpdateTokenPairUseCaseImpl
import com.universall.auth_impl.domain.utils.network.AuthenticatedApiClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthDomainBindingModule {
    @Binds
    @Singleton
    abstract fun bindGetActiveSessionsUseCase(impl: GetActiveSessionsUseCaseImpl): GetActiveSessionsUseCase

    @Binds
    @Singleton
    abstract fun bindGetMeUseCase(impl: GetMeUseCaseImpl): GetMeUseCase

    @Binds
    @Singleton
    abstract fun bindGetTokenPairUseCase(impl: GetTokenPairUseCaseImpl): GetTokenPairUseCase

    @Binds
    @Singleton
    abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    @Singleton
    abstract fun bindLogoutUseCase(impl: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    @Singleton
    abstract fun bindRefreshUseCase(impl: RefreshUseCaseImpl): RefreshUseCase

    @Binds
    @Singleton
    abstract fun bindRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    @Singleton
    abstract fun bindRestoreAuthStateUseCase(impl: RestoreAuthStateUseCaseImpl): RestoreAuthStateUseCase

    @Binds
    @Singleton
    abstract fun bindRevokeAllSessionsExceptCurrentUseCase(impl: RevokeAllSessionsExceptCurrentUseCaseImpl): RevokeAllSessionsExceptCurrentUseCase

    @Binds
    @Singleton
    abstract fun bindRevokeSessionUseCase(impl: RevokeSessionUseCaseImpl): RevokeSessionUseCase

    @Binds
    @Singleton
    abstract fun bindUpdateTokenPairUseCase(impl: UpdateTokenPairUseCaseImpl): UpdateTokenPairUseCase

    @Binds
    @Singleton
    @AuthenticatedApiClient
    abstract fun bindAuthenticatedApiClient(impl: AuthenticatedApiClientImpl): CoreApiClient

    @Binds
    @Singleton
    abstract fun bindLocalLogoutUseCase(impl: LocalLogoutUseCaseImpl): LocalLogoutUseCase
}
