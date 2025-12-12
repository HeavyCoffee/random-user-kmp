package com.heavycoffee.core.database.di

import com.heavycoffee.core.database.data.database.AppDatabase
import com.heavycoffee.core.database.data.database.UserDao
import com.heavycoffee.core.database.data.datasource.UserDataSourceImpl
import com.heavycoffee.core.database.domain.datasource.UserDataSource
import com.heavycoffee.core.database.domain.usecase.GetUserByIdUseCase
import com.heavycoffee.core.database.domain.usecase.GetUserByIdUseCaseImpl
import com.heavycoffee.core.database.domain.usecase.IsUserListNotEmptyUseCase
import com.heavycoffee.core.database.domain.usecase.IsUserListNotEmptyUseCaseImpl
import com.heavycoffee.core.database.domain.usecase.ObserveUserListUseCase
import com.heavycoffee.core.database.domain.usecase.ObserveUserListUseCaseUseCaseImpl
import com.heavycoffee.core.database.domain.usecase.SaveUserUseCase
import com.heavycoffee.core.database.domain.usecase.SaveUserUseCaseImpl
import org.koin.dsl.module

val koinDatabaseModule = module {
    factory<UserDataSource> { UserDataSourceImpl(dao = get()) }
    provideDatabase()
    single<UserDao> { get<AppDatabase>().getUserDao() }
    factory<IsUserListNotEmptyUseCase> { IsUserListNotEmptyUseCaseImpl(userDataSource = get()) }
    factory<ObserveUserListUseCase> { ObserveUserListUseCaseUseCaseImpl(userDataSource = get()) }
    factory<GetUserByIdUseCase> { GetUserByIdUseCaseImpl(userDataSource = get()) }
    factory<SaveUserUseCase> { SaveUserUseCaseImpl(userDataSource = get()) }
}