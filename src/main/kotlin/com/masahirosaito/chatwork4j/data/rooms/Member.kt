package com.masahirosaito.chatwork4j.data.rooms

/**
 * Created by masahiro on 2017/01/19.
 */
data class Member(
        val account_id: Int,
        val role: String,
        val name: String,
        val chatwork_id: String,
        val organization_id: Int,
        val organization_name: String,
        val department: String,
        val avatar_image_url: String
)