package ru.netology

data class DirectMessage(
    val idChat: UInt,
    val owner: People,
    val user: People,
    val message: MutableList<Message>? = mutableListOf(),
    var isDelete: Boolean //true - delete, false - is active
)
