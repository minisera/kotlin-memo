package com.example.domain.exception

class NotFoundException(id: Int): Throwable("not found. id = $id")