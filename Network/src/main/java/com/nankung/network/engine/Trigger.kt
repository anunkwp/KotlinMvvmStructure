package com.nankung.network.engine

/**
 * * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */
abstract class Trigger(isForceFetch: Boolean) {
    var isForceFetch = false

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val trigger = o as Trigger
        return isForceFetch == trigger.isForceFetch
    }

    override fun hashCode(): Int {
        return if (isForceFetch) 1 else 0
    }

    init {
        this.isForceFetch = isForceFetch
    }
}