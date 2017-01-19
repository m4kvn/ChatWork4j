package com.masahirosaito.chatwork4j.data.contacts

/**
 * Created by masahiro on 2017/01/19.
 */
data class Contact(
        val account_id: Int,
        val room_id: Int,
        val name: String,
        val chatwork_id: String,
        val organization_id: Int,
        val organization_name: String,
        val department: String,
        val avatar_image_url: String
)