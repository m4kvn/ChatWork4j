---
title: ChatWork4j - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j](../index.md) / [ChatWork4j](.)

# ChatWork4j

`class ChatWork4j` [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/ChatWork4j.kt#L25)

ChatWork API を利用するためのクラス

### Parameters

`TOKEN` - ChatWork API TOKEN

### Constructors

| [&lt;init&gt;](-init-.md) | `ChatWork4j(TOKEN: String)`<br>ChatWork API を利用するためのクラス |

### Functions

| [deleteRoom](delete-room.md) | `fun deleteRoom(room_id: Int, action_type: `[`ActionType`](../-action-type/index.md)`): Unit`<br>チャットルームを削除・退席 |
| [getContacts](get-contacts.md) | `fun getContacts(): Array<`[`Contact`](../../com.masahirosaito.chatwork4j.data.contacts/-contact/index.md)`>`<br>自分のコンタクト一覧を取得 |
| [getFile](get-file.md) | `fun getFile(room_id: Int, fileId: Int, createDownloadUrl: Boolean = false): `[`File`](../../com.masahirosaito.chatwork4j.data.rooms/-file/index.md)<br>チャットルームのファイルを取得 |
| [getFiles](get-files.md) | `fun getFiles(room_id: Int, accountId: Int? = null): Array<`[`File`](../../com.masahirosaito.chatwork4j.data.rooms/-file/index.md)`>`<br>チャットルームのファイル一覧を取得 |
| [getMe](get-me.md) | `fun getMe(): `[`Me`](../../com.masahirosaito.chatwork4j.data.me/-me/index.md)<br>自分の情報を取得 |
| [getMembers](get-members.md) | `fun getMembers(room_id: Int): Array<`[`Member`](../../com.masahirosaito.chatwork4j.data.rooms/-member/index.md)`>`<br>チャットルームのメンバー一覧を取得 |
| [getMessage](get-message.md) | `fun getMessage(room_id: Int, messageId: Int): `[`Message`](../../com.masahirosaito.chatwork4j.data.rooms/-message/index.md)<br>チャットルームのメッセージを取得 |
| [getMessages](get-messages.md) | `fun getMessages(room_id: Int, force: Boolean = false): Array<`[`Message`](../../com.masahirosaito.chatwork4j.data.rooms/-message/index.md)`>`<br>チャットルームのメッセージ一覧を取得 |
| [getMyStatus](get-my-status.md) | `fun getMyStatus(): `[`Status`](../../com.masahirosaito.chatwork4j.data.my/-status/index.md)<br>自分の状態を取得 |
| [getMyTasks](get-my-tasks.md) | `fun getMyTasks(): Array<`[`Task`](../../com.masahirosaito.chatwork4j.data.my/-task/index.md)`>`<br>自分のタスク一覧を取得 |
| [getRoom](get-room.md) | `fun getRoom(room_id: Int): `[`Room`](../../com.masahirosaito.chatwork4j.data.rooms/-room/index.md)<br>チャットルームを取得 |
| [getRooms](get-rooms.md) | `fun getRooms(): Array<`[`Room`](../../com.masahirosaito.chatwork4j.data.rooms/-room/index.md)`>`<br>チャットルーム一覧を取得 |
| [getTask](get-task.md) | `fun getTask(room_id: Int, taskId: Int): `[`Task`](../../com.masahirosaito.chatwork4j.data.my/-task/index.md)<br>チャットルームのタスクを取得 |
| [getTasks](get-tasks.md) | `fun getTasks(room_id: Int, account_id: Int? = null, assigned_by_account_id: Int? = null, status: `[`TaskStatus`](../../com.masahirosaito.chatwork4j.data.my/-task-status/index.md)`? = null): Array<`[`Task`](../../com.masahirosaito.chatwork4j.data.my/-task/index.md)`>`<br>チャットルームのタスク一覧を取得 |
| [postMessage](post-message.md) | `fun postMessage(room_id: Int, message: String): `[`MessageResponse`](../../com.masahirosaito.chatwork4j.data/-message-response/index.md)<br>チャットルームにメッセージを投稿 |
| [postRoom](post-room.md) | `fun postRoom(name: String, members_admin_ids: Array<Int>, members_member_ids: Array<Int>? = null, members_readonly_ids: Array<Int>? = null, description: String? = null, icon_preset: `[`IconPreset`](../-icon-preset/index.md)`? = null): `[`RoomResponse`](../../com.masahirosaito.chatwork4j.data/-room-response/index.md)<br>チャットルームを作成 |
| [putMembers](put-members.md) | `fun putMembers(room_id: Int, members_admin_ids: Array<Int>, members_member_ids: Array<Int>? = null, members_readonly_ids: Array<Int>? = null): `[`MemberResponse`](../../com.masahirosaito.chatwork4j.data/-member-response/index.md)<br>チャットルームのメンバーを更新 |
| [putRoom](put-room.md) | `fun putRoom(room_id: Int, description: String? = null, icon_preset: `[`IconPreset`](../-icon-preset/index.md)`? = null, name: String? = null): `[`RoomResponse`](../../com.masahirosaito.chatwork4j.data/-room-response/index.md)<br>チャットルームの情報を更新 |

### Companion Object Properties

| [CHATWORK_API_TOKEN_HEADER](-c-h-a-t-w-o-r-k_-a-p-i_-t-o-k-e-n_-h-e-a-d-e-r.md) | `val CHATWORK_API_TOKEN_HEADER: String`<br>ChatWork API Header |
| [CHATWORK_API_URL_ROOT](-c-h-a-t-w-o-r-k_-a-p-i_-u-r-l_-r-o-o-t.md) | `val CHATWORK_API_URL_ROOT: String`<br>ChatWork API URL |

