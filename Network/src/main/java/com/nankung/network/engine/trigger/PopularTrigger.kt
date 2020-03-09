package com.nankung.network.engine.trigger

import com.nankung.network.engine.Trigger


class PopularTrigger(
    var apiKey: String
) : Trigger(true) {

    override
    fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as PopularTrigger
        if (apiKey != other.apiKey) return false
        return true
    }

    override
    fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + apiKey.hashCode()
        return result
    }
}

