package com.heavycoffee.core.database.domain.model

data class User(
    val id: Long,
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: DateOfBirth,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val idDocument: IdDocument,
    val picture: Picture,
    val nat: String
) {
    data class Name(
        val title: String,
        val first: String,
        val last: String
    ) {
        val fullName: String = "$first $last"
    }

    data class Location(
        val street: Street,
        val city: String,
        val state: String,
        val country: String,
        val postcode: String,
        val coordinates: Coordinates,
        val timezone: Timezone
    ) {
        data class Street(
            val number: Int,
            val name: String
        ) {
            val fullInfo: String = "$name, $number"
        }

        data class Coordinates(
            val latitude: String,
            val longitude: String
        )

        data class Timezone(
            val offset: String,
            val description: String
        ) {
            val fullInfo: String = "$offset, $description"
        }
    }

    data class Login(
        val uuid: String,
        val username: String,
        val password: String,
        val salt: String,
        val md5: String,
        val sha1: String,
        val sha256: String
    )

    data class DateOfBirth(
        val date: String,
        val age: Int
    )

    data class Registered(
        val date: String,
        val age: Int
    )

    data class IdDocument(
        val name: String,
        val value: String
    )

    data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
    )
}