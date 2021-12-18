package com.example.route

import com.example.controller.MemoController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Routing.root() {
    val memoController: MemoController by inject()

    route("/memos") {
        @KtorExperimentalLocationsAPI
        @Location("/{memoId}")
        data class MemoParam(
            val memoId: Int
        )

        get {
            call.respond(memoController.all())
        }

        get<MemoParam> {
            call.respond(memoController.getMemoById(it.memoId))
        }

        post {
            memoController.create(call.receive())
            call.respond(HttpStatusCode.Created)
        }
    }

}
