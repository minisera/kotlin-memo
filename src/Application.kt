package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    Database.connect(
        "jdbc:mysql://127.0.0.1:33060/kotlin_memo",
//        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "mysql"
    )


    transaction {
        addLogger(StdOutSqlLogger)

        val id = Memo.insert {
            it[id] = 2
            it[title] = "初めての本"
            it[body] = "いい本でした"
        } get Memo.id
        println("id: $id")

        val memo = Memo.select{ Memo.id eq id }.single()
        println("memo: $memo")

    }
}

object Memo: Table("memos") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 256)
}