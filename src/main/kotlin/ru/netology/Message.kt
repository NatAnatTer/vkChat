package ru.netology

data class Message(
    val idMessage: UInt,
    val targetPeople: People,
    var text: String,
    val dateCreate: Long,
    var isDelete: Boolean, //true - delete, false - is active
    var targetPeopleIsRead: Boolean,
)
