package com.example.lab2

import android.os.Parcelable
import com.example.kbtu_lab2_network.network.CatDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    var length: String? = null,
    var origin: String? = null,
    var imageLink: String? = null,
    var familyFriendly: Int? = null,
    var shedding: Int? = null,
    var generalHealth: Int? = null,
    var playfulness: Int? = null,
    var childrenFriendly: Int? = null,
    var grooming: Int? = null,
    var intelligence: Int? = null,
    var otherPetsFriendly: Int? = null,
    var minWeight: Float? = null,
    var maxWeight: Float? = null,
    var minLifeExpectancy: Float? = null,
    var maxLifeExpectancy: Float? = null,
    var name: String? = null
) : Parcelable

internal fun CatDto.toCat(): Cat = Cat(
    length = this.length,
    origin = this.origin,
    imageLink = this.imageLink,
    familyFriendly = this.familyFriendly,
    shedding = this.shedding,
    generalHealth = this.generalHealth,
    playfulness = this.playfulness,
    childrenFriendly = this.childrenFriendly,
    grooming = this.grooming,
    intelligence = this.intelligence,
    otherPetsFriendly = this.otherPetsFriendly,
    minWeight = this.minWeight,
    maxWeight = this.maxWeight,
    minLifeExpectancy = this.minLifeExpectancy,
    maxLifeExpectancy = this.maxLifeExpectancy,
    name = this.name
)
