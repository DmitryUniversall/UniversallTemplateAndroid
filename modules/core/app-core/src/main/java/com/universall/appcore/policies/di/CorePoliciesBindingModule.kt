package com.universall.appcore.policies.di

import com.universall.appcore.policies.impl.app_code.AppCodeProcessingPolicyImpl
import com.universall.core.policies.app_code.AppCodeProcessingPolicy
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CorePoliciesBindingModule {
    @Binds
    @Singleton
    abstract fun bindAppCodeProcessingPolicy(impl: AppCodeProcessingPolicyImpl): AppCodeProcessingPolicy
}
