package com.heavycoffee.core.backendapi.domain.usecase

import com.heavycoffee.core.backendapi.domain.model.RandomUserParams

abstract class GetGenderListUseCase {
    abstract operator fun invoke(): List<RandomUserParams.Gender>
}

internal class GetGenderListUseCaseImpl : GetGenderListUseCase() {
    override fun invoke(): List<RandomUserParams.Gender> =
        listOf(RandomUserParams.Gender.Male, RandomUserParams.Gender.Female)
}