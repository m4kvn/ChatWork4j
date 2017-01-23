package com.masahirosaito.chatwork4j.data

/**
 * Created by masahiro on 2017/01/23.
 */
data class MemberResponse(
        val admin: Array<Int> ,
        val member: Array<Int>,
        val readonly: Array<Int>
)