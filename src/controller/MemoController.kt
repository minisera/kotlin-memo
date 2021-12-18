package com.example.controller

import com.example.domain.Memo
import com.example.domain.MemoRequest
import com.example.service.MemoService

class MemoController(private val memoService: MemoService) {

    /**
     * Memoを全件取得する
     * @return Memo
     */
    fun all(): List<Memo> {
        return memoService.all()
    }

    /**
     * Memoを登録する
     * @param memoRequest
     */
    fun create(memoRequest: MemoRequest) {
        val memo = Memo(
            id = null,
            title = memoRequest.title,
            body = memoRequest.body
        )

        memoService.create(memo)
    }

    /**
     * idでMemoを取得する
     * @param memoId
     * @return Memo
     */
    fun getMemoById(memoId: Int): Memo = memoService.get(memoId)
}