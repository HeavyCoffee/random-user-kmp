package com.heavycoffee.core.backendapi.domain.model

data class RandomUserParams(
    val gender: Gender? = null,
    val nationality: List<Nationality>? = null
) {
    sealed class Gender(val name: String) {
        data object Female : Gender("female")
        data object Male : Gender("male")
    }

    sealed class Nationality(val code: String) {
        object AU : Nationality("au")
        object BR : Nationality("br")
        object CA : Nationality("ca")
        object CH : Nationality("ch")
        object DE : Nationality("de")
        object DK : Nationality("dk")
        object ES : Nationality("es")
        object FI : Nationality("fi")
        object FR : Nationality("fr")
        object GB : Nationality("gb")
        object IE : Nationality("ie")
        object IN : Nationality("in")
        object IR : Nationality("ir")
        object MX : Nationality("mx")
        object NL : Nationality("nl")
        object NO : Nationality("no")
        object NZ : Nationality("nz")
        object RS : Nationality("rs")
        object TR : Nationality("tr")
        object UA : Nationality("ua")
        object US : Nationality("us")
    }
}