package ru.netology

fun main() {
    val peopleOne = People(1U, "Nata", "Наталья")
    val peopleTwo = People(2U, "Artem", "Артем")
    val peopleThree = People(3U, "Vika", "Виктория")
    val peopleFour = People(4U, "Arkadiy", "Аркадий")
    val persons: MutableSet<People> = mutableSetOf(peopleOne, peopleTwo, peopleThree, peopleFour)
    val obj = DirectMessageService
//   obj.addDirectMessages(peopleOne, peopleTwo, "Hello. First message")
//    obj.addDirectMessages(peopleOne,peopleThree, "Hola!!!!")
//    obj.addDirectMessages(peopleTwo, peopleOne, "Hi. Second message")
//    obj.addDirectMessages(peopleThree, peopleOne, "Hi. Second message")
//    obj.addDirectMessages(peopleFour, peopleOne, "Hi. Second message")
//    obj.addDirectMessages(peopleOne, peopleTwo, "How do you do?. Three message")
//    obj.addDirectMessages(peopleTwo, peopleOne, "I am fine, thanks. And you?. First message")
//    obj.addDirectMessages(peopleOne, peopleTwo, "Me too. First message")
//    obj.printDirectMessages()
//    obj.printMessage()
//    println("@@@@@@@@@@@@@@@@@@@@@@@@")
//    obj.deleteDirectMessages(peopleOne, peopleTwo)
//    obj.printDirectMessages()
//   println( obj.getDirectMessages(peopleOne))
    // println( obj.getUnreadChatsCount(peopleOne))
    //  val editedMessage = Message(1U, peopleTwo, "EDITED TEXT", 1650023223661, false, true)
//obj.editMessage(peopleOne, peopleTwo, 1U, "EDITED TEXT")
//    obj.deleteMessage(peopleOne, peopleTwo, 1U)

//   println("@@@@@@@@@@@@@@@@@@@@@@@@")
//    obj.printDirectMessages()
//print(obj.getMessages(1U, 2U, 2, peopleTwo))


    val owner = authorization(persons)
  //  openDirectMessage(persons, owner)


}

fun authorization(persons: MutableSet<People>): People {
    while (true) {
        print("Авторизация. Введите логин:")
        val login = readLine()
        for (user in persons) {
            if (user.login == login) {
                println("Добро пожаловать ${user.name}")
                return user
            }
        }
        println("Пользователь не найден, проверьте правильность ввода логина")
        continue
    }
}

fun selectCommand(owner: People, persons: MutableSet<People>) {
    while (true) {
        println(
            "Выберите действие:" + "\n" +
                    "1. Получить информацию о количестве непрочитанных чатов" + "\n" + //getUnreadChatsCount(owner: People)
                    "2. Получить список чатов" + "\n" + //getDirectMessages(owner: People)
                    "3. Получить список сообщений из чата" + "\n" + //getMessages(idChat: UInt,idMessageStart: UInt,numberOfMessages: Int,owner: People)
                    "4. Создать сообщение" + "\n" + //addDirectMessages(owner: People, targetPeople: People, text: String)
                    "5. Редактировать сообщение" + "\n" + //editMessage(owner: People, targetPeople: People, idMessage: UInt, text: String)
                    "6. Удалить сообщение" + "\n" + //deleteMessage(owner: People, targetPeople: People, idMessage: UInt)
                    "7. Удалить чат" + "\n" + //deleteDirectMessages(owner: People, targetPeople: People)
                    "8. Выйти из аккаунта"
        )

        val userCommandChoice: UInt
        try {
            userCommandChoice = readLine()?.toUInt() ?: return
        } catch (e: Exception) {
            println("Введена не допустимая команда")
            continue
        }
        if (userCommandChoice > 8U) {
            println("Введена не допустимая команда, повторите попытку снова")
            continue
        }

        when (userCommandChoice) {
            1U -> DirectMessageService.getUnreadChatsCount(owner)
            2U -> DirectMessageService.getDirectMessages(owner)
            3U -> DirectMessageService.getMessages(
                userChoiseMessageChat("Введите ID чата"),
                userChoiseMessageChat("Введите ID сообщения"),
                userChoiseCountMessage("Введите количество сообщений"),
                owner
            )
            4U -> getTextMessage("Введите текст сообщения")?.let {
                DirectMessageService.addDirectMessages(
                    owner, getTargetPeople("Введите имя собеседника", persons),
                    it
                )
            }
            5U -> getTextMessage("Введите текст сообщения")?.let {
                DirectMessageService.editMessage(owner, getTargetPeople("Введите имя собеседника", persons),userChoiseIdMessage("Введите ID сообщения"),
                    it
                )
            }
                6U -> DirectMessageService.deleteMessage(owner, getTargetPeople("Введите имя собеседника", persons), userChoiseIdMessage("Введите ID сообщения"),
                7U -> DirectMessageService.deleteDirectMessages(owner, getTargetPeople("Введите имя собеседника", persons)),
                8U-> return
        }

    }
}

fun userChoiseMessageChat(text: String): UInt {
    while (true) {
        println(text)
        val userCommandChoice: UInt
        try {
            userCommandChoice = readLine()?.toUInt() ?: return 0U
        } catch (e: Exception) {
            println("Введена не допустимая команда")
            continue
        }
        return userCommandChoice
    }
}

fun userChoiseIdMessage(text: String): UInt {
    while (true) {
        println(text)
        val userCommandChoice: UInt
        try {
            userCommandChoice = readLine()?.toUInt() ?: return 0U
        } catch (e: Exception) {
            println("Введена не допустимая команда")
            continue
        }
        return userCommandChoice
    }
}

fun userChoiseCountMessage(text: String): Int {
    while (true) {
        println(text)
        val userCommandChoice: Int
        try {
            userCommandChoice = readLine()?.toInt() ?: return 0
        } catch (e: Exception) {
            println("Введена не допустимая команда")
            continue
        }
        return userCommandChoice
    }
}

fun getTargetPeople(text: String, persons: MutableSet<People>): People {
    while (true) {
        print(text)
        val name = readLine()
        for (user in persons) {
            if (user.name == name) {
                return user
            }
        }
        println("Пользователь не найден, проверьте правильность ввода логина")
        continue
    }
}

fun getTextMessage(text: String): String? {
    print(text)
    return readLine()
}

//fun openDirectMessage(persons: MutableSet<People>, owner: People) {
//    while (true) {
//        print("Введите имя собеседника:")
//        val companion = readLine()
//        for (user in persons) {
//            if (user.name == companion) {
//                //  DirectMessageService.addDirectMessages(owner, user)
//                return
//            }
//        }
//        println("Собеседник не найден, попробуйте снова")
//        continue
//    }
//}