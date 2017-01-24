package com.masahirosaito.chatwork4j.error

import java.util.*

class ResponseError {
    val errors: Array<String>

    constructor(errors: Array<String>) {
        this.errors = errors
    }

    override fun toString(): String {
        return "ResponseError(errors=${Arrays.toString(errors)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ResponseError

        if (!Arrays.equals(errors, other.errors)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(errors)
    }
}