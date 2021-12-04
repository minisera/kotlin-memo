package com.example.module

import com.example.controller.MemoController
import com.example.repository.MemoRepository
import com.example.repository.implement.MemoRepositoryImpl
import com.example.service.MemoService
import org.koin.dsl.module
import org.koin.experimental.builder.single

val memoModule = module {
    single<MemoController>()
    single<MemoRepository>{ MemoRepositoryImpl() }
    single<MemoService>()
}

