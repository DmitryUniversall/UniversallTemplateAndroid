package com.universall.appcore.policies.di

import com.universall.appcore.policies.app_code.AppCodeProcessingPolicy
import com.universall.appcore.policies.app_code.AppCodeProcessingPolicyImpl
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
