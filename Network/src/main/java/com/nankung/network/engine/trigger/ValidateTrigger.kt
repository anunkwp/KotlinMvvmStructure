package com.nankung.network.engine.trigger

import com.nankung.network.engine.Trigger
import com.nankung.network.model.response.body.ValidateBody


class ValidateTrigger(
    var apiKey: String,
    var validateBody: ValidateBody
) : Trigger(true) {

    override
    fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ValidateTrigger
        if (apiKey != other.apiKey) return false
        if (validateBody != other.validateBody) return false
        return true
    }

    override
    fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + apiKey.hashCode()
        result = 31 * result + validateBody.hashCode()
        return result
    }
}

