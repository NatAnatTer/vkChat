package ru.netology


object DirectMessageService {
    val directMessages = mutableListOf<DirectMessage>()

    fun addDirectMessages(owner: People, targetPeople: People, text: String): Boolean {

        if (findChatInDirectMessages(owner, targetPeople, false) != null) {
            findChatInDirectMessages(owner, targetPeople, false)?.let { createMessage(text, it, targetPeople) }
            println("Чат открыт")
            return true
        } else if (findChatInDirectMessages(owner, targetPeople, true) != null) {
            findChatInDirectMessages(owner, targetPeople, true)?.isDelete = false
            findChatInDirectMessages(owner, targetPeople, true)?.let { createMessage(text, it, targetPeople) }
            println("Чат открыт")
            return true
        } else {
            val newId = (directMessages.lastOrNull()?.idChat ?: 0U) + 1U
            val directMessageAdding = DirectMessage(newId, owner, targetPeople, null, false)
            directMessages.add(directMessageAdding)
            createMessage(text, directMessageAdding, targetPeople)
            println("Чат создан")
            return true
        }
    }

    private fun findChatInDirectMessages(owner: People, targetPeople: People, isDelete: Boolean): DirectMessage? {
        return if (!isDelete) {
            directMessages.find {
                (it.user1 == owner && it.user2 == targetPeople && !it.isDelete) ||
                        (it.user2 == owner && it.user1 == targetPeople && !it.isDelete)
            }

        } else {

            directMessages.find {
                (it.user1 == owner && it.user2 == targetPeople && it.isDelete) ||
                        (it.user2 == owner && it.user1 == targetPeople && it.isDelete)
            }
        }
    }

    private fun createMessage(text: String, directMessage: DirectMessage, targetPeople: People) {
        val idMessage = (directMessage.message?.lastOrNull()?.idMessage ?: 0U) + 1U
        val message = Message(
            idMessage,
            targetPeople,
            text,
            System.currentTimeMillis(),
            isDelete = false,
            targetPeopleIsRead = false,
        )
        println(message)
        directMessages
            .filter { it == directMessage }
            .onEach { it.message = ((it.message?.plus(message) ?: mutableListOf(message)) as MutableList<Message>?) }
    }


    fun deleteDirectMessages(owner: People, targetPeople: People): Boolean {
        val deletedChat = findChatInDirectMessages(owner, targetPeople, false)
        if (deletedChat != null) {
            true.also { deletedChat.isDelete = it }
            deletedChat.message?.forEach { deletedChat.message!!.forEach { _ -> it.isDelete = true } }
            println("Чат удален")
            return true
        }
        return false
    }

    fun getDirectMessages(owner: People): List<DirectMessage> {
        val emptyChat = "нет сообщений"
        val resultChats: List<DirectMessage> = directMessages
            .filter { (it.user1 == owner && !it.isDelete) || (it.user2 == owner && !it.isDelete) }
            .onEach { println(it) }

        resultChats
            .ifEmpty { emptyChat }
            .let { println(it) }
        return resultChats
    }


    fun getUnreadChatsCount(owner: People): Int {
        val countChat = directMessages.count { directMessage ->
            directMessage.message?.any { it -> it.targetPeople == owner && !it.targetPeopleIsRead } ?: false
        }
        if (countChat == 0) {
            println("Непрочитанных чатов нет")
        } else {
            println(countChat)
        }
        return countChat
    }

    fun editMessage(owner: People, targetPeople: People, idMessage: UInt, text: String): Boolean {

        directMessages.asSequence()
            .filter {
                (it.user1 == owner && it.user2 == targetPeople && !it.isDelete) ||
                        (it.user2 == owner && it.user1 == targetPeople && !it.isDelete)
            }
            .onEach {
                it.message?.asSequence()
                    ?.filter { message -> message.idMessage == idMessage }
                    ?.onEach { message -> message.text = text }
                    ?.onEach { message -> println(message) }
                    ?.count()
            }
            .count()

        return true
    }

    fun deleteMessage(owner: People, targetPeople: People, idMessage: UInt): Boolean {
        directMessages.asSequence()
            .filter {
                (it.user1 == owner && it.user2 == targetPeople && !it.isDelete) ||
                        (it.user2 == owner && it.user1 == targetPeople && !it.isDelete)
            }
            .onEach {
                it.message?.asSequence()
                    ?.filter { message -> message.idMessage == idMessage }
                    ?.onEach { message -> message.isDelete = true }
                    ?.count()
            }
            .count()

        directMessages
            .filter {
                (it.user1 == owner && it.user2 == targetPeople && !it.isDelete) ||
                        (it.user2 == owner && it.user1 == targetPeople && !it.isDelete)
            }
            .filter { (it.message?.count { message -> !message.isDelete } ?: 0) == 0 }
            .forEach { it.isDelete = true }
        println("Сообщение удалено")
        return true
    }


    fun getMessages(
        idChat: UInt,
        idMessageStart: UInt,
        numberOfMessages: Int,
        owner: People
    ): MutableList<Message> {

        val resultList = mutableListOf<Message>()
        val resultChat = directMessages
            .find { it.idChat == idChat && !it.isDelete }

        resultChat?.message?.filter { it.idMessage >= idMessageStart && !it.isDelete }
            ?.filterIndexedTo(resultList) { index, _ -> index <= (numberOfMessages - 1) }?: println("Сообщений в чате нет")

        resultList
            .filter { it.targetPeople == owner }
            .forEach { it.targetPeopleIsRead = true }

        resultList
            .forEach { println(it) }

        return resultList

    }

}



