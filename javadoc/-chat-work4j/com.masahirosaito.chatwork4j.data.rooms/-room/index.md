---
title: Room - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j.data.rooms](../index.md) / [Room](.)

# Room

`data class Room` [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/data/rooms/Room.kt#L21)

Roomデータクラス

### Constructors

| [&lt;init&gt;](-init-.md) | `Room(room_id: Int, name: String, type: String, role: String, sticky: Boolean, unread_num: Int, mention_num: Int, mytask_num: Int, message_num: Int, file_num: Int, task_num: Int, icon_path: String, last_update_time: Long, description: String)`<br>Roomデータクラス |

### Properties

| [description](description.md) | `val description: String`<br>概要 |
| [file_num](file_num.md) | `val file_num: Int`<br>ファイル数 |
| [icon_path](icon_path.md) | `val icon_path: String`<br>アイコン画像URL |
| [last_update_time](last_update_time.md) | `val last_update_time: Long`<br>最後の更新時間 |
| [mention_num](mention_num.md) | `val mention_num: Int`<br>自分宛てメッセージ数 |
| [message_num](message_num.md) | `val message_num: Int`<br>メッセージ数 |
| [mytask_num](mytask_num.md) | `val mytask_num: Int`<br>自分のタスク数 |
| [name](name.md) | `val name: String`<br>名前 |
| [role](role.md) | `val role: String`<br>役割 |
| [room_id](room_id.md) | `val room_id: Int`<br>ルームID |
| [sticky](sticky.md) | `val sticky: Boolean`<br>ピン留め |
| [task_num](task_num.md) | `val task_num: Int`<br>タスク数 |
| [type](type.md) | `val type: String`<br>タイプ |
| [unread_num](unread_num.md) | `val unread_num: Int`<br>未読メッセージ数 |

