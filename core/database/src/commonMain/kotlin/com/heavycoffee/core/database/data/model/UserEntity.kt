package com.heavycoffee.core.database.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heavycoffee.core.database.domain.model.User

@Entity
internal data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val gender: String,
    @Embedded
    val name: Name,
    @Embedded
    val location: Location,
    val email: String,
    @Embedded
    val login: Login,
    @Embedded
    val dob: DateOfBirth,
    @Embedded
    val registered: Registered,
    val phone: String,
    val cell: String,
    @Embedded
    val idDocument: IdDocument,
    @Embedded
    val picture: Picture,
    val nat: String
) {
    companion object {
        fun fromDomain(user: User) = UserEntity(
            gender = user.gender,
            name = Name.fromDomain(user.name),
            location = Location.fromDomain(user.location),
            email = user.email,
            login = Login.fromDomain(user.login),
            dob = DateOfBirth.fromDomain(user.dob),
            registered = Registered.fromDomain(user.registered),
            phone = user.phone,
            cell = user.cell,
            idDocument = IdDocument.fromDomain(user.idDocument),
            picture = Picture.fromDomain(user.picture),
            nat = user.nat
        )
    }
    fun toDomain() = User(
        id = id,
        gender = gender,
        name = name.toDomain(),
        location = location.toDomain(),
        email = email,
        login = login.toDomain(),
        dob = dob.toDomain(),
        registered = registered.toDomain(),
        phone = phone,
        cell = cell,
        idDocument = idDocument.toDomain(),
        picture = picture.toDomain(),
        nat = nat
    )

    data class Name(
        val title: String,
        val first: String,
        val last: String
    ) {
        companion object {
            fun fromDomain(name: User.Name) = Name(
                title = name.title,
                first = name.first,
                last = name.last
            )
        }
        fun toDomain() = User.Name(
            title = title,
            first = first,
            last = last
        )
    }

    data class Location(
        @Embedded
        val street: Street,
        val city: String,
        val state: String,
        val country: String,
        val postcode: String,
        @Embedded
        val coordinates: Coordinates,
        @Embedded
        val timezone: Timezone
    ) {
        companion object {
            fun fromDomain(location: User.Location) = Location(
                street = Street.fromDomain(location.street),
                city = location.city,
                state = location.state,
                country = location.country,
                postcode = location.postcode,
                coordinates = Coordinates.fromDomain(location.coordinates),
                timezone = Timezone.fromDomain(location.timezone)
            )
        }
        fun toDomain() = User.Location(
            street = street.toDomain(),
            city = city,
            state = state,
            country = country,
            postcode = postcode,
            coordinates = coordinates.toDomain(),
            timezone = timezone.toDomain()
        )
        data class Street(
            val number: Int,
            @ColumnInfo("street_name")
            val name: String
        ) {
            companion object {
                fun fromDomain(street: User.Location.Street) = Street(
                    number = street.number,
                    name = street.name
                )
            }
            fun toDomain() = User.Location.Street(
                number = number,
                name = name
            )
        }

        data class Coordinates(
            val latitude: String,
            val longitude: String
        ) {
            companion object {
                fun fromDomain(coordinates: User.Location.Coordinates) = Coordinates(
                    latitude = coordinates.latitude,
                    longitude = coordinates.longitude
                )
            }
            fun toDomain() = User.Location.Coordinates(
                latitude = latitude,
                longitude = longitude
            )
        }

        data class Timezone(
            val offset: String,
            val description: String
        ) {
            companion object {
                fun fromDomain(timezone: User.Location.Timezone) = Timezone(
                    offset = timezone.offset,
                    description = timezone.description
                )
            }
            fun toDomain() = User.Location.Timezone(
                offset = offset,
                description = description
            )
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
    ) {
        companion object {
            fun fromDomain(login: User.Login) = Login(
                uuid = login.uuid,
                username = login.username,
                password = login.password,
                salt = login.salt,
                md5 = login.md5,
                sha1 = login.sha1,
                sha256 = login.sha256
            )
        }
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

    data class DateOfBirth(
        @ColumnInfo("dob_date")
        val date: String,
        @ColumnInfo("dob_age")
        val age: Int
    ) {
        companion object {
            fun fromDomain(dob: User.DateOfBirth) = DateOfBirth(
                date = dob.date,
                age = dob.age
            )
        }
        fun toDomain() = User.DateOfBirth(
            date = date,
            age = age
        )
    }

    data class Registered(
        val date: String,
        val age: Int
    ) {
        companion object {
            fun fromDomain(registered: User.Registered) = Registered(
                date = registered.date,
                age = registered.age
            )
        }
        fun toDomain() = User.Registered(
            date = date,
            age = age
        )
    }

    data class IdDocument(
        val name: String,
        val value: String
    ) {
        companion object {
            fun fromDomain(id: User.IdDocument) = IdDocument(
                name = id.name,
                value = id.value
            )
        }
        fun toDomain() = User.IdDocument(
            name = name,
            value = value
        )
    }

    data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
    ) {
        companion object {
            fun fromDomain(picture: User.Picture) = Picture(
                large = picture.large,
                medium = picture.medium,
                thumbnail = picture.thumbnail
            )
        }
        fun toDomain() = User.Picture(
            large = large,
            medium = medium,
            thumbnail = thumbnail
        )
    }
}