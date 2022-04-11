package ru.netology

object DirectMessageService {
    private val directMessages = mutableListOf<DirectMessage>()

    fun addDirectMessage(owner: People, user: People): Boolean {
        for (chat in directMessages) {
            if (chat.owner == owner && chat.user == user && !chat.isDelete) {
                println("Чат открыт")
                return true
            } else if (chat.owner == owner && chat.user == user && chat.isDelete) {
                chat.isDelete = false
                println("Чат открыт")
                return true
            }
        }
        val newId = (directMessages.lastOrNull()?.idChat ?: 0U) + 1U
        val directMessageAdding = DirectMessage(newId, owner, user, null, false)
        directMessages += directMessageAdding
        return true

    }

//    fun addMessage(message: Message, directMessage: DirectMessage): Boolean {
//        if (directMessage.idChat == 0U) {
//            val newId = (directMessages.lastOrNull()?.idChat ?: 0U) + 1U
//            val directMessageAdding = directMessage.copy(
//                idChat = newId
//            )
//            directMessageAdding.message += message
//            directMessages += directMessageAdding
//            return true
//        }
//        for (chat in directMessages) {
//            if (directMessage.idChat == chat.idChat && !chat.isDelete) {
//                chat.message += message
//                return true
//            } else if (directMessage.idChat == chat.idChat && chat.isDelete) {
//                chat.isDelete = false
//                chat.message += message
//                return true
//            }
//        }
//        return false
//    }


}