---
title: ChatWork4j.putMembers - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j](../index.md) / [ChatWork4j](index.md) / [putMembers](.)

# putMembers

`fun putMembers(room_id: Int, members_admin_ids: Array<Int>, members_member_ids: Array<Int>? = null, members_readonly_ids: Array<Int>? = null): `[`MemberResponse`](../../com.masahirosaito.chatwork4j.data/-member-response/index.md) [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/ChatWork4j.kt#L230)

チャットルームのメンバーを更新

### Parameters

`room_id` - チャットルームID

`members_admin_ids` - 管理者権限のアカウントID一覧

`members_member_ids` - メンバー権限のアカウントID一覧

`members_readonly_ids` - 閲覧のみ権限のアカウントID一覧

**Return**
更新したチャットルームのアカウント一覧を持ったオブジェクト

