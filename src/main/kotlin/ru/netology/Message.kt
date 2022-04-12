package ru.netology

data class Message(
    val idMessage: UInt,
    val targetPeople: People,
    val text: String,
    val dateCreate: Long,
    var isDelete: Boolean, //true - delete, false - is active
    var targetPeopleIsRead: Boolean,
)
