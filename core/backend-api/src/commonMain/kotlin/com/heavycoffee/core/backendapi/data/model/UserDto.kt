package com.heavycoffee.core.backendapi.data.model

import com.heavycoffee.core.backendapi.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    val login: LoginDto,
    val dob: DateOfBirthDto,
    val registered: RegisteredDto,
    val phone: String,
    val cell: String,
    val id: IdDto,
    val picture: PictureDto,
    val nat: String
) {
    fun toDomain() = User(
        gender = gender,
        name = name.toDomain(),
        location = location.toDomain(),
        email = email,
        login = login.toDomain(),
        dob = dob.toDomain(),
        registered = registered.toDomain(),
        phone = phone,
        cell = cell,
        id = id.toDomain(),
        picture = picture.toDomain(),
        nat = nat
    )
    @Serializable
    data class NameDto(
        val title: String,
        val first: String,
        val last: String
    ) {
        fun toDomain() = User.Name(
            title = title,
            first = first,
            last = last
        )
    }

    @Serializable
    data class LocationDto(
        val street: StreetDto,
        val city: String,
        val state: String,
        val country: String,
        val postcode: String,
        val coordinates: CoordinatesDto,
        val timezone: TimezoneDto
    ) {
        fun toDomain() = User.Location(
            street = street.toDomain(),
            city = city,
            state = state,
            country = country,
            postcode = postcode,
            coordinates = coordinates.toDomain(),
            timezone = timezone.toDomain()
        )

        @Serializable
        data class StreetDto(
            val number: Int,
            val name: String
        ) {
            fun toDomain() = User.Location.Street(
                number = number,
                name = name
            )
        }
        @Serializable
        data class CoordinatesDto(
            val latitude: String,
            val longitude: String
        ) {
            fun toDomain() = User.Location.Coordinates(
                latitude = latitude,
                longitude = longitude
            )
        }

        @Serializable
        data class TimezoneDto(
            val offset: String,
            val description: String
        ) {
            fun toDomain() = User.Location.Timezone(
                offset = offset,
                description = description
            )
        }
    }

    @Serializable
    data class LoginDto(
        val uuid: String,
        val username: String,
        val password: String,
        val salt: String,
        val md5: String,
        val sha1: String,
        val sha256: String
    ) {
        fun toDomain() = User.Login(
            uuid = uuid,
            username = username,
            password = password,
            salt = salt,
            md5 = md5,
            sha1 = sha1,
            sha256 = sha256
        )
    }

    @Serializable
    data class DateOfBirthDto(
        val date: String,
        val age: Int
    ) {
        fun toDomain() = User.DateOfBirth(
            date = date,
            age = age
        )
    }

    @Serializable
    data class RegisteredDto(
        val date: String,
        val age: Int
    ) {
        fun toDomain() = User.Registered(
            date = date,
            age = age
        )
    }

    @Serializable
    data class IdDto(
        val name: String,
        val value: String
    ) {
        fun toDomain() = User.Id(
            name = name,
            value = value
        )
    }

    @Serializable
    data class PictureDto(
        val large: String,
        val medium: String,
        val thumbnail: String
    ) {
        fun toDomain() = User.Picture(
            large = large,
            medium = medium,
            thumbnail = thumbnail
        )
    }
}