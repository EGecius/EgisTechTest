package com.egecius.egisbabylontechtest.postdetail

data class UserJson(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressJson,
    val phone: String,
    val website: String,
    val company: CompanyJson
)

data class AddressJson(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoJson
)

data class CompanyJson(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

data class GeoJson(
    val lat: String,
    val lng: String
)
