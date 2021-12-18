package com.example.service

import com.example.domain.Memo
import com.example.repository.MemoRepository
import io.ktor.features.*
import org.jetbrains.exposed.sql.transactions.transaction

class MemoService(private val memoRepository: MemoRepository) {

    /**
     * 全件取得する
     * @return Memos全件
     */
    fun all(): List<Memo> = transaction { memoRepository.all() }

    /**
     * Memosを登録する
     * @param memo
     * @return id
     */
    fun create(memo: Memo): Int = transaction { memoRepository.create(memo) }

    /**
     * Memosを取得する
     * @param memoId
     * return Memo
     */
    fun get(memoId: Int): Memo {
        return transaction {
            memoRepository.findById(memoId) ?: throw NotFoundException("not found record. id = $memoId")
        }
    }
}