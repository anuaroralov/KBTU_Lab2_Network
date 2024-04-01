package com.example.kbtu_lab2_network.network

import com.google.gson.annotations.SerializedName

data class CatDto(
    @SerializedName("length") var length: String? = null,
    @SerializedName("origin") var origin: String? = null,
    @SerializedName("image_link") var imageLink: String? = null,
    @SerializedName("family_friendly") var familyFriendly: Int? = null,
    @SerializedName("shedding") var shedding: Int? = null,
    @SerializedName("general_health") var generalHealth: Int? = null,
    @SerializedName("playfulness") var playfulness: Int? = null,
    @SerializedName("children_friendly") var childrenFriendly: Int? = null,
    @SerializedName("grooming") var grooming: Int? = null,
    @SerializedName("intelligence") var intelligence: Int? = null,
    @SerializedName("other_pets_friendly") var otherPetsFriendly: Int? = null,
    @SerializedName("min_weight") var minWeight: Int? = null,
    @SerializedName("max_weight") var maxWeight: Int? = null,
    @SerializedName("min_life_expectancy") var minLifeExpectancy: Int? = null,
    @SerializedName("max_life_expectancy") var maxLifeExpectancy: Int? = null,
    @SerializedName("name") var name: String? = null
)


