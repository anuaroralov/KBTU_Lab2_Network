package com.example.lab2

data class Request(
    var name: String? = null,
    var minWeight: Int? = null,
    var maxWeight: Int? = null,
    var minLifeExpectancy: Int? = null,
    var maxLifeExpectancy: Int? = null,
    var shedding: Int? = null,
    var familyFriendly: Int? = null,
    var playfulness: Int? = null,
    var grooming: Int? = null,
    var otherPetsFriendly: Int? = null,
    var childrenFriendly: Int? = null,
    var offset: Int? = null
) {
    fun isEmpty(): Boolean {
        return name.isNullOrEmpty() &&
                minWeight == null &&
                maxWeight == null &&
                minLifeExpectancy == null &&
                maxLifeExpectancy == null &&
                shedding == null &&
                familyFriendly == null &&
                playfulness == null &&
                grooming == null &&
                otherPetsFriendly == null &&
                childrenFriendly == null &&
                offset == null
    }
}
