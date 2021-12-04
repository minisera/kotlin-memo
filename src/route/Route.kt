package com.example.route

import com.example.controller.MemoController
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Routing.root() {
    val memoController: MemoController by inject()
    route("/memos") {
        get {
            call.respond(memoController.all())
        }
    }
}
