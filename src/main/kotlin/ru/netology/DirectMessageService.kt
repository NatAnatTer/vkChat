package ru.netology

object DirectMessageService {
    private val directMessages = mutableListOf<DirectMessage>()

    fun addDirectMessages(owner: People, targetPeople: People, text: String): Boolean {
        for (chat in directMessages) {
            if ((chat.user1 == owner && chat.user2 == targetPeople && !chat.isDelete) || (chat.user2 == owner && chat.user1 == targetPeople && !chat.isDelete)) {
                createMessage(text, chat, targetPeople)
                println("Чат открыт")
                return true
            } else if ((chat.user1 == owner && chat.user2 == targetPeople && chat.isDelete) || (chat.user2 == owner && chat.user1 == targetPeople && chat.isDelete)) {
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

    fun createMessage(text: String, directMessage: DirectMessage, targetPeople: People) {
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

//    fun addDirectMessageTo(owner: People, user: People, text: String): Boolean {
//        for (chat in directMessages) {
//            if (chat.owner == user && chat.user == owner && !chat.isDelete) {
//                createMessageTo(text, chat, owner, user)
//                println("Чат открыт")
//                return true
//            } else if (chat.owner == user && chat.user == owner && chat.isDelete) {
//                chat.isDelete = false
//                createMessageFrom(text, chat, owner, user)
//                println("Чат открыт")
//                return true
//            }
//        }
//        val newId = (directMessages.lastOrNull()?.idChat ?: 0U) + 1U
//        val directMessageAdding = DirectMessage(newId, user, owner, null, false)
//        directMessages += directMessageAdding
//        createMessageFrom(text, directMessageAdding, owner, user)
//        println("Чат создан")
//        return true
//    }

//    private fun createMessageFrom(text: String, directMessage: DirectMessage, owner: People, user: People) {
//        if (directMessage.message.isNullOrEmpty()) {
//            val idMessage = 1U
//            val message = Message(
//                idMessage,
//                owner,
//                user,
//                text,
//                System.currentTimeMillis(),
//                isDelete = false,
//                ownerRead = true,
//                userRead = false
//            )
//            directMessage.message?.add(message)
//        }
//    }


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

    fun getDirectMessages(owner: People): MutableList<DirectMessage> {
        val resultList: MutableList<DirectMessage> = mutableListOf()
        for (chat in directMessages) {
            if (chat.owner == owner && !chat.isDelete) {
                resultList.add(chat)
            }
        }
        if (resultList.isEmpty()) {
            println("нет сообщений")
            return resultList
        }
        return resultList
    }

    fun getUnreadChatsCount(owner: People): UInt {
        var countChat: UInt = 0U
        for (chat in directMessages) {
            if (chat.owner == owner && !chat.isDelete) {
                var flag = false
                for (message in chat.message!!) {
                    if (!message.ownerRead) {
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

    fun editMessage(owner: People, user: People, editedMessage: Message): Boolean {
        for (chat in directMessages) {
            if (chat.owner == owner && chat.user == user && !chat.isDelete) {
                for ((index) in chat.message?.withIndex()!!) {
                    if (chat.message[index].idMessage == editedMessage.idMessage) {
                        chat.message[index] = editedMessage.copy()
                        editMessageTo(owner, user, editedMessage)
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun editMessageTo(owner: People, user: People, editedMessage: Message) {
        for (chat in directMessages) {
            if (chat.owner == user && chat.user == owner && !chat.isDelete) {
                for ((index) in chat.message?.withIndex()!!) {
                    if (chat.message[index].idMessage == editedMessage.idMessage && !chat.message[index].isDelete) {
                        chat.message[index] = editedMessage.copy()
                        return
                    }
                }
            }
        }
    }

    fun deleteMessage(owner: People, user: People, deletedMessage: Message): Boolean {
        for (chat in directMessages) {
            if (chat.owner == owner && chat.user == user && !chat.isDelete) {
                for (message in chat.message!!) {
                    if (message.idMessage == deletedMessage.idMessage && !message.isDelete) {
                        message.isDelete = true
                        if (chat.message.count() == 1) {
                            chat.isDelete = true
                        }
                        deleteMessageTo(owner, user, deletedMessage)
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun deleteMessageTo(owner: People, user: People, deletedMessage: Message) {
        for (chat in directMessages) {
            if (chat.owner == user && chat.user == owner && !chat.isDelete) {
                for (message in chat.message!!) {
                    if (message.idMessage == deletedMessage.idMessage && !message.isDelete) {
                        message.isDelete = true
                        if (chat.message.count() == 1) {
                            chat.isDelete = true
                        }
                    }
                }
            }
        }
    }

    fun getMessages(idChat: UInt, idMessageStart: UInt, numberOfMessages: Int): MutableList<Message> {
        val resultList = mutableListOf<Message>()
        for (chat in directMessages) {
            if (chat.idChat == idChat && !chat.isDelete) {
                var numberMessage = numberOfMessages
                for (message in chat.message!!) {
                    if (message.idMessage >= idMessageStart && !message.isDelete && numberMessage > 0) {
                        message.userRead = true
                        resultList.add(message)
                        numberMessage -= 1
                    }
                }
            }
        }
        return resultList
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


