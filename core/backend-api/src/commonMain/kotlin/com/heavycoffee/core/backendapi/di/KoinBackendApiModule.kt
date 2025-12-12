package com.heavycoffee.core.backendapi.di
import com.heavycoffee.core.backendapi.data.api.RandoUserApi
import com.heavycoffee.core.backendapi.data.api.RandoUserApiImpl
import com.heavycoffee.core.backendapi.data.datasource.RandomUserDataSourceImpl
import com.heavycoffee.core.backendapi.domain.datasource.RandomUserDataSource
import com.heavycoffee.core.backendapi.domain.usecase.GenerateUserUseCase
import com.heavycoffee.core.backendapi.domain.usecase.GenerateUserUseCaseImpl
import com.heavycoffee.core.backendapi.domain.usecase.GetGenderListUseCase
import com.heavycoffee.core.backendapi.domain.usecase.GetGenderListUseCaseImpl
import com.heavycoffee.core.backendapi.domain.usecase.GetNationalityListUseCase
import com.heavycoffee.core.backendapi.domain.usecase.GetNationalityListUseCaseImpl
import org.koin.dsl.module

val koinBackendApiModule = module {
    factory<RandomUserDataSource> { RandomUserDataSourceImpl(api = get()) }
    factory<RandoUserApi> { RandoUserApiImpl(client = get()) }
    factory<GenerateUserUseCase> { GenerateUserUseCaseImpl(randomUserDataSource = get()) }
    factory<GetGenderListUseCase> { GetGenderListUseCaseImpl() }
    factory<GetNationalityListUseCase> { GetNationalityListUseCaseImpl() }
}