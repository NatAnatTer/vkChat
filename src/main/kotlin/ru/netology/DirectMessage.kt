package ru.netology

data class DirectMessage(
    val idChat: UInt,
    val user1: People,
    val user2: People,
    val message: MutableList<Message>? = mutableListOf(),
    var isDelete: Boolean //true - delete, false - is active
)
