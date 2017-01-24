package com.masahirosaito.chatwork4j.data

import java.util.*

/** MemberResponseクラス */
class MemberResponse {

    /** 管理者権限アカウントID一覧 */
    val admin: Array<Int>

    /** メンバー権限アカウントID一覧 */
    val member: Array<Int>

    /** 閲覧のみ権限アカウントID一覧 */
    val readonly: Array<Int>

    /**
     * コンストラクタ
     *
     * @param admin 管理者権限アカウントID一覧
     * @param member メンバー権限アカウントID一覧
     * @param readonly 閲覧のみ権限アカウントID一覧
     */
    constructor(admin: Array<Int>, member: Array<Int>, readonly: Array<Int>) {
        this.admin = admin
        this.member = member
        this.readonly = readonly
    }

    /**
     * 文字列として取得
     *
     * @return オブジェクトの文字列
     */
    override fun toString(): String {
        return "MemberResponse(admin=${Arrays.toString(admin)}" +
                ", member=${Arrays.toString(member)}" +
                ", readonly=${Arrays.toString(readonly)})"
    }

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