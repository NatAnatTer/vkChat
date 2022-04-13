package ru.netology

object DirectMessageService {
    private val directMessages = mutableListOf<DirectMessage>()

    fun addDirectMessages(owner: People, targetPeople: People, text: String): Boolean {
        for (chat in directMessages) {
            if ((chat.user1 == owner && chat.user2 == targetPeople && !chat.isDelete) ||
                (chat.user2 == owner && chat.user1 == targetPeople && !chat.isDelete)
            ) {
                createMessage(text, chat, targetPeople)
                println("Чат открыт")
                return true
            } else if ((chat.user1 == owner && chat.user2 == targetPeople && chat.isDelete) ||
                (chat.user2 == owner && chat.user1 == targetPeople && chat.isDelete)
            ) {
                chat.isDelete = false
                createMessage(text, chat, targetPeople)
                println("Чат открыт")
                return true
            }
        }
        val newId = (directMessages.lastOrNull()?.idChat ?: 0U) + 1U
        val directMessageAdding = DirectMessage(newId, owner, targetPeople, null, false)
        directMessages.add(directMessageAdding)
        createMessage(text, directMessageAdding, targetPeople)
        println("Чат создан")
        return true
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
        directMessage.message?.add(message)
    }



    fun deleteDirectMessages(owner: People, user: People) {
        // val deletedChat = directMessages.find{owner:People , user:People -> }
        for (chat in directMessages) {
            if ((chat.user1 == owner && chat.user2 == user && !chat.isDelete) ||
                (chat.user2 == owner && chat.user1 == user && !chat.isDelete)
            ) {
                chat.isDelete = true
                for (message in chat.message!!) {
                    message.isDelete = true
                }
                println("Чат удален")
            }
        }
    }

    fun getDirectMessages(owner: People): MutableList<DirectMessage> {
        val resultList: MutableList<DirectMessage> = mutableListOf()
        for (chat in directMessages) {
            if ((chat.user1 == owner && !chat.isDelete) || (chat.user2 == owner && !chat.isDelete)) {
                resultList.add(chat)
            }
        }
        if (resultList.isEmpty()) {
            println("нет сообщений")
        }
        return resultList
    }

    fun getUnreadChatsCount(owner: People): UInt {
        var countChat: UInt = 0U
        for (chat in directMessages) {
            if ((chat.user1 == owner && !chat.isDelete) || (chat.user2 == owner && !chat.isDelete)) {
                var flag = false
                for (message in chat.message!!) {
                    if (message.targetPeople == owner && !message.targetPeopleIsRead) {
                        flag = true
                    }
                }
                if (flag) {
                    countChat += 1U
                }
            }
        }
        return countChat
    }

    //#################################
    fun getUnreadChatsCountExperiment(owner: People): UInt {
val directMessage: DirectMessage
        var countChat: UInt =0U
            //var countChat: Int = directMessages.count(; ->(directMessage.user1 == owner && !chat.isDelete) || (chat.user2 == owner && !chat.isDelete)))



        for (chat in directMessages) {
            if ((chat.user1 == owner && !chat.isDelete) || (chat.user2 == owner && !chat.isDelete)) {
                var flag = false
                for (message in chat.message!!) {
                    if (message.targetPeople == owner && !message.targetPeopleIsRead) {
                        flag = true
                    }
                }
                if (flag) {
                    countChat += 1U
                }
            }
        }
        return countChat
    }
    //####################################

    fun editMessage(owner: People, user: People, editedMessage: Message): Boolean {
        for (chat in directMessages) {
            if ((chat.user1 == owner && chat.user2 == user && !chat.isDelete) ||
                (chat.user2 == owner && chat.user1 == user && !chat.isDelete)
            ) {
                for ((index) in chat.message?.withIndex()!!) {
                    if (chat.message[index].idMessage == editedMessage.idMessage) {
                        chat.message[index] = editedMessage.copy(
                            dateCreate = chat.message[index].dateCreate
                        )
                        return true
                    }
                }
            }
        }
        return false
    }

    fun deleteMessage(owner: People, user: People, deletedMessage: Message): Boolean {
        for (chat in directMessages) {
            if ((chat.user1 == owner && chat.user2 == user && !chat.isDelete) ||
                (chat.user2 == owner && chat.user1 == user && !chat.isDelete)
            ) {
               // if(chat.message?.withIndex().contains(deletedMessage) == true){
                for (message in chat.message!!) {
                    if (message.idMessage == deletedMessage.idMessage && !message.isDelete) {
                        message.isDelete = true
                        if (chat.message.count() == 1) {
                            chat.isDelete = true
                        }
                        return true
                    }
                }
            }
        }
        return false
    }


    fun getMessages(idChat: UInt, idMessageStart: UInt, numberOfMessages: Int, owner: People): MutableList<Message> {
        val resultList = mutableListOf<Message>()
        for (chat in directMessages) {
            if (chat.idChat == idChat && !chat.isDelete) {
                var numberMessage = numberOfMessages
                for (message in chat.message!!) {
                    if (message.idMessage >= idMessageStart && !message.isDelete && numberMessage > 0) {
                        if (message.targetPeople == owner){
                            message.targetPeopleIsRead = true
                        }
                        resultList.add(message)
                        numberMessage -= 1
                    }
                }
            }
        }
        return resultList
    }

//######################
    fun getMessagesExperiment(idChat: UInt, idMessageStart: UInt, numberOfMessages: Int, owner: People): MutableList<Message> {
        val resultList = mutableListOf<Message>()
        for (chat in directMessages) {
            if (chat.idChat == idChat && !chat.isDelete) {

                var numberMessage = numberOfMessages
                for (message in chat.message!!) {
                    if (message.idMessage >= idMessageStart && !message.isDelete && numberMessage > 0) {
                        if (message.targetPeople == owner){
                            message.targetPeopleIsRead = true
                        }
                        resultList.add(message)
                        numberMessage -= 1
                    }
                }
            }
        }
        return resultList
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

}








