package ru.netology

object DirectMessageService {
    val directMessages = mutableListOf<DirectMessage>()

    //######################################
    fun printDirectMessages() {
        directMessages.forEach { println(it) }
    }

    fun printMessage() {
        for (chat in directMessages) {
            println(chat.idChat)
            chat.message?.forEach { println(it) }
        }
    }
    //##################################

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

        directMessages.asSequence()
            .filter { it == directMessage }
            .onEach { it.message = ((it.message?.plus(message) ?: mutableListOf(message)) as MutableList<Message>?) }
            .count()
    }


    fun deleteDirectMessages(owner: People, targetPeople: People) {
        val deletedChat = findChatInDirectMessages(owner, targetPeople, false)
        if (deletedChat != null) {
            deletedChat.isDelete = true
          //  deletedChat.message?.forEach { deletedChat.message.forEach { _ -> it.isDelete = true } }
            println("Чат удален")
        }
    }

//    fun getDirectMessages(owner: People): List<DirectMessage>? {
//        val resultChats = directMessages.filter { it.user1 == owner || it.user2 == owner }
//        if (resultChats == null) {
//            println("нет сообщений")
//        }
//        return resultChats
//    }


//    fun getUnreadChatsCount(owner: People): Int {
//        return directMessages.count { directMessage ->
//            directMessage
//                .message?.none { message -> message.targetPeople == owner && !message.targetPeopleIsRead }
//                ?: false
//        }
//
//    }

//    fun editMessage(owner: People, targetPeople: People, editedMessage: Message): Boolean {
//        val editedChat = findChatInDirectMessages(owner, targetPeople, false)
//        if (editedChat != null) {
//            for ((index) in editedChat.message?.withIndex()!!) {
////                if (editedChat.message[index].idMessage == editedMessage.idMessage) {
////                    editedChat.message[index] = editedMessage.copy(
////                        dateCreate = editedChat.message[index].dateCreate
//                    )
//                    editDirectMessages(editedChat)
//                    return true
//                }
//            }
//        }
//
//        return false
//    }

    //  fun deleteMessage(owner: People, targetPeople: People, deletedMessage: Message): Boolean {
//        val editedChat = findChatInDirectMessages(owner, targetPeople, false)
//        if (editedChat != null) {
//            for (message in editedChat.message!!) {
//                if (message.idMessage == deletedMessage.idMessage && !message.isDelete) {
//                    message.isDelete = true
//                    if (editedChat.message.count() == 1) {
//                        editedChat.isDelete = true
//                    }
//                    editDirectMessages(editedChat)
//                    return true
//                }
//            }
//        }
//        return false
//    }

//    fun getMessages(
//        idChat: UInt,
//        idMessageStart: UInt,
//        numberOfMessages: Int,
//        owner: People
//    ): MutableList<Message> {
//        val resultList = mutableListOf<Message>()
//        directMessages.stream()
//            .filter{ directMessages->directMessages.idChat == idChat && !directMessages.isDelete}
//            .peek{ directMessages->directMessages.isDelete=true}
//            .peek( directMessages-> )
//            .collect{Collectors.toList<>()};

//        val resultChat = directMessages.find { it.idChat == idChat && !it.isDelete }
//        var numberMessage = numberOfMessages
//        if (resultChat != null) {
//            for (message in resultChat.message!!) {
//                if (message.idMessage >= idMessageStart && !message.isDelete && numberMessage > 0) {
//                    if (message.targetPeople == owner) {
//                        message.targetPeopleIsRead = true
//                    }
//                    resultList.add(message)
//                    numberMessage -= 1
//                }
//            }
//        }
//        return resultList
//    }
}

//    val cats = listOf("Мурзик", "Барсик", "Рыжик")
//    println(cats.get(2))
//    Но у этих способов есть один недостаток - если вы укажете неправильное значение индекса, то получите исключение ArrayIndexOutOfBoundsException. Вы можете избежать проблемы, если будете использовать метод getOrElse() с указанием значения по умолчанию, если выйдете за пределы допустимых значений. В этом случае вам не придётся обрабатывать исключение.
//
//
//    val cats = listOf("Мурзик", "Барсик", "Рыжик")
//    println(cats.getOrElse(4) { "Неизвестный котик" })
//// или как вариант, имя первого кота
//    println(cats.getOrElse(4) { cats.first() })
//#######################


//        for (chat in directMessages) {
//            if ((chat.user1 == owner && chat.user2 == targetPeople && !chat.isDelete) ||
//                (chat.user2 == owner && chat.user1 == targetPeople && !chat.isDelete)
//            ) {
//                createMessage(text, chat, targetPeople)
//                println("Чат открыт")
//                return true
//            } else if ((chat.user1 == owner && chat.user2 == targetPeople && chat.isDelete) ||
//                (chat.user2 == owner && chat.user1 == targetPeople && chat.isDelete)
//            ) {
//                chat.isDelete = false
//                createMessage(text, chat, targetPeople)
//                println("Чат открыт")
//                return true
//            }
//        }
//        val newId = (directMessages.lastOrNull()?.idChat ?: 0U) + 1U
//        val directMessageAdding = DirectMessage(newId, owner, targetPeople, null, false)
//        directMessages.add(directMessageAdding)
//        createMessage(text, directMessageAdding, targetPeople)
//        println("Чат создан")
//        return true


//for (chat in directMessages) {
//            if ((chat.user1 == owner && chat.user2 == targetPeople && !chat.isDelete) ||
//                (chat.user2 == owner && chat.user1 == targetPeople && !chat.isDelete)
//            ) {
//                chat.isDelete = true
//                for (message in chat.message!!) {
//                    message.isDelete = true
//                }
//                println("Чат удален")
//            }
//        }

//        val resultList: MutableList<DirectMessage> = mutableListOf()
//        for (chat in directMessages) {
//            if ((chat.user1 == owner && !chat.isDelete) || (chat.user2 == owner && !chat.isDelete)) {
//                resultList.add(chat)
//            }
//        }
//        if (resultList.isEmpty()) {
//            println("нет сообщений")
//        }

//    fun getUnreadChatsCount(owner: People): UInt {
//        var countChat: UInt = 0U
//        for (chat in directMessages) {
//            if ((chat.user1 == owner && !chat.isDelete) || (chat.user2 == owner && !chat.isDelete)) {
//                var flag = false
//                for (message in chat.message!!) {
//                    if (message.targetPeople == owner && !message.targetPeopleIsRead) {
//                        flag = true
//                    }
//                }
//                if (flag) {
//                    countChat += 1U
//                }
//            }
//        }
//        return countChat
//    }


//        directMessages.stream()
//            .filter{ directMessages->directMessages.idChat == idChat && !directMessages.isDelete}
//            .peek{ directMessages->directMessages.isDelete=true}
//            .peek( directMessages-> )
//            .collect{Collectors.toList<>()};


// private fun editDirectMessages(directMessage: DirectMessage) {
//        for ((index) in directMessages.withIndex()) {
//            if (directMessages[index].idChat == directMessage.idChat) {
//                directMessages[index] = directMessage.copy(
//                )
//            }
//        }
//    }



