package com.nankung.network.engine.trigger

import com.nankung.network.engine.Trigger


class PopularTrigger(
    var apiKey: String,
    var language :String
) : Trigger(true) {

    override
    fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as PopularTrigger
        if (apiKey != other.apiKey) return false
        if (language != other.language) return false
        return true
    }

    override
    fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + apiKey.hashCode()
        result = 31 * result + language.hashCode()
        return result
    }
}

