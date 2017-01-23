package com.masahirosaito.chatwork4j.data

import java.util.*

/**
 * Created by masahiro on 2017/01/23.
 */
class MemberResponse {
    val admin: Array<Int>
    val member: Array<Int>
    val readonly: Array<Int>

    constructor(admin: Array<Int>, member: Array<Int>, readonly: Array<Int>) {
        this.admin = admin
        this.member = member
        this.readonly = readonly
    }

    override fun toString(): String {
        return "MemberResponse(admin=${Arrays.toString(admin)}" +
                ", member=${Arrays.toString(member)}" +
                ", readonly=${Arrays.toString(readonly)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as MemberResponse

        if (!Arrays.equals(admin, other.admin)) return false
        if (!Arrays.equals(member, other.member)) return false
        if (!Arrays.equals(readonly, other.readonly)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(admin)
        result = 31 * result + Arrays.hashCode(member)
        result = 31 * result + Arrays.hashCode(readonly)
        return result
    }
}