package ru.netology

object DirectMessageService {
    private val directMessages = mutableListOf<DirectMessage>()

    fun addDirectMessages(owner: People, user: People, text: String): Boolean {
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
        println("Чат создан")
        return true
    }

    fun createMessageFrom(text: String, directMessage: DirectMessage, owner: People, user: People) {
        if (directMessage.message.isNullOrEmpty()) {
            val idMessage = 1U
            val message = Message(
                idMessage,
                owner,
                user,
                text,
                System.currentTimeMillis(),
                isDelete = false,
                ownerRead = true,
                userRead = false
            )
            directMessage.message?.add(message)
        }
    }

    fun createMessageTo(text: String, directMessage: DirectMessage, owner: People, user: People) {
        val idMessage = (directMessage.message?.lastOrNull()?.idMessage ?: 0U) + 1U
        val message = Message(
            idMessage,
            user,
            owner,
            text,
            System.currentTimeMillis(),
            isDelete = false,
            ownerRead = false,
            userRead = true
        )
        directMessage.message?.add(message)
    }

    fun deleteDirectMessages(owner: People, user: People) {
        // val deletedChat = directMessages.find{owner:People , user:People -> }
        for (chat in directMessages) {
            if (chat.owner == owner && chat.user == user && !chat.isDelete) {
                chat.isDelete = true
                for (message in chat.message!!) {
                    message.isDelete = true
                }
                println("Чат удален")
            }
            if (chat.owner == user && chat.user == owner && !chat.isDelete) {
                chat.isDelete = true
                for (message in chat.message!!) {
                    message.isDelete = true
                }
                println("Чат собеседника удален")
            }
        }
    }

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


