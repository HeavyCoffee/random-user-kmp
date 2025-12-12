package com.heavycoffee.core.backendapi.domain.usecase

import com.heavycoffee.core.backendapi.domain.model.RandomUserParams

abstract class GetNationalityListUseCase {
    abstract  operator fun invoke(): List<RandomUserParams.Nationality>
}

internal class GetNationalityListUseCaseImpl : GetNationalityListUseCase() {
    override fun invoke(): List<RandomUserParams.Nationality> = listOf(
        RandomUserParams.Nationality.AU,
        RandomUserParams.Nationality.BR,
        RandomUserParams.Nationality.CA,
        RandomUserParams.Nationality.CH,
        RandomUserParams.Nationality.DE,
        RandomUserParams.Nationality.DK,
        RandomUserParams.Nationality.ES,
        RandomUserParams.Nationality.FI,
        RandomUserParams.Nationality.FR,
        RandomUserParams.Nationality.GB,
        RandomUserParams.Nationality.IE,
        RandomUserParams.Nationality.IN,
        RandomUserParams.Nationality.IR,
        RandomUserParams.Nationality.MX,
        RandomUserParams.Nationality.NL,
        RandomUserParams.Nationality.NO,
        RandomUserParams.Nationality.NZ,
        RandomUserParams.Nationality.RS,
        RandomUserParams.Nationality.TR,
        RandomUserParams.Nationality.UA,
        RandomUserParams.Nationality.US
    )
}