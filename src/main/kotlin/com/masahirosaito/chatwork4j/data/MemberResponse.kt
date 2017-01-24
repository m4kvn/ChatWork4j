package com.masahirosaito.chatwork4j.data

import java.util.*

/**
 * MemberResponse
 *
 * @property admin 管理者権アカウントID一覧
 * @property member メンバー権限アカウントID一覧
 * @property readonly 閲覧のみ権限アカウントID一覧
 */
data class MemberResponse(
    val admin: Array<Int>,
    val member: Array<Int>,
    val readonly: Array<Int>
) {
    /**
     * 他のオブジェクトと比較
     *
     * @param other 比較対象
     * @return 一緒ならtrue
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as MemberResponse

        if (!Arrays.equals(admin, other.admin)) return false
        if (!Arrays.equals(member, other.member)) return false
        if (!Arrays.equals(readonly, other.readonly)) return false

        return true
    }

    /**
     * ハッシュコードを取得
     *
     * @return ハッシュコード
     */
    override fun hashCode(): Int {
        var result = Arrays.hashCode(admin)
        result = 31 * result + Arrays.hashCode(member)
        result = 31 * result + Arrays.hashCode(readonly)
        return result
    }
}