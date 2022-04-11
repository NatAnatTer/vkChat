package ru.netology

data class Message(
    val idMessage: UInt,
    val owner: People,
    val user: People,
    val text: String,
    val dateCreate: Long,
    var isDelete: Boolean, //true - delete, false - is active
    val ownerRead: Boolean,
    val userRead: Boolean
)
